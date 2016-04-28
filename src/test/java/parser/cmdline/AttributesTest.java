package parser.cmdline;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static parser.utils.Utils.getArrayFrom;
import static parser.utils.Utils.asStream;

import org.apache.commons.cli.Option;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;

/**
 * TODO - Remove all "anyTimes". Each "anyTimes" call is the result of a
 * performance issue. Once we are sure each option is read one time, we can
 * remove this "anyTimes".
 */
public class AttributesTest {
    private Option[] mockedOptions = null;

    @After
    public void tearDown() {
        asStream(mockedOptions).forEach(EasyMock::verify);
        mockedOptions = null;
    }

    private Attributes createAttributesObj(Option... options) {
        asStream(options).forEach(EasyMock::replay);
        return new Attributes(mockedOptions = options);
    }

    @Test
    public void attributesHasFile() {
        Option fileOption = createMock(Option.class);
        expect(fileOption.getLongOpt()).andReturn(OptionsAvailable.SOURCE_FILE.longName).anyTimes();
        expect(fileOption.getValues()).andReturn(getArrayFrom("/path/to/sourceFile")).anyTimes();
        Attributes attributes = createAttributesObj(fileOption);
        assertThat(attributes.getSourceFiles(), hasSize(1));
        assertThat(attributes.getSourceFiles().get(0).toString(), is(equalTo("/path/to/sourceFile")));
    }

    @Test
    public void nullSafeFileOption() {
        Option fileOption = createMock(Option.class);
        expect(fileOption.getLongOpt()).andReturn(OptionsAvailable.SOURCE_FILE.longName).anyTimes();
        expect(fileOption.getValues()).andReturn(null).anyTimes();
        Attributes attributes = createAttributesObj(fileOption);
        assertThat(attributes.getSourceFiles(), is(empty()));
    }

    @Test
    public void attributesHasDebugFlag() {
        Option fileOption = createMock(Option.class);
        expect(fileOption.getLongOpt()).andReturn(OptionsAvailable.DEBUG.longName).anyTimes();
        expect(fileOption.getValues()).andReturn(getArrayFrom("true")).anyTimes();
        expect(fileOption.getValue()).andReturn("true").anyTimes();
        Attributes attributes = createAttributesObj(fileOption);
        assertThat(attributes.isDebugMode(), is(true));
    }

    @Test
    public void attributesHasDefaultDebugFlag() {
        Attributes attributes = new Attributes(null);
        assertThat(attributes.isDebugMode(), is(false));
    }
}
