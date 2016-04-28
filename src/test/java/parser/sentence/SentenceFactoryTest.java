package parser.sentence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

public class SentenceFactoryTest {
	private static SentenceFactory sentenceFactory;
	private static SimpleSentence sentence;
	
	@BeforeClass
	public static void setUp () {
		sentenceFactory = new SentenceFactory(';');
		sentence = sentenceFactory.processSentence("a=1;");
	}

	// TODO - Remove null
    @Test
    public void sentenceHasReturnedType () {
        assertThat(sentence.getReturnedType(), is(equalTo(null)));
    }
}
