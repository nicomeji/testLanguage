package parser.cmdline;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Properties;

import org.apache.commons.cli.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OptionsProcessorTest {
    private static final String DESCRIPTION = "Description";

    private OptionsProcessor optionProcessor;
    private static Properties propMock;

    @Before
    public void setUp() {
        propMock = createMock(Properties.class);
        expect(propMock.getProperty(anyString())).andReturn(DESCRIPTION).times(OptionsAvailable.values().length);
        replay(propMock);
        optionProcessor = new OptionsProcessor(propMock);
    }

    @After
    public void tearDown() {
        verify(propMock);
    }

    @Test
    public void parseSourceFilesOpt_1() throws ParseException {
        Attributes attributes = optionProcessor.process(getArgs("-f", "/path/to/sourceFile"));
        assertThat(attributes.getSourceFiles().size(), is(1));
        assertThat(attributes.getSourceFiles().get(0).toString(), is(equalTo("/path/to/sourceFile")));
    }

    @Test
    public void parseSourceFilesOpt_2() throws ParseException {
        Attributes attributes = optionProcessor.process(getArgs("-f", "/path/to/sourceFile1", "/path/to/sourceFile2"));
        assertThat(attributes.getSourceFiles().size(), is(2));
        assertThat(attributes.getSourceFiles(), containsInAnyOrder(new File("/path/to/sourceFile1"), new File("/path/to/sourceFile2")));
    }

    private String[] getArgs(String... a) {
        return a;
    }
}
