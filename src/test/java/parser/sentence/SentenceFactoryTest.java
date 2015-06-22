package parser.sentence;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class SentenceFactoryTest {
	private static SentenceFactory sentenceFactory;
	private static Sentence sentence;
	
	@BeforeClass
	public static void setUp () {
		sentenceFactory = new SentenceFactory();
		sentence = sentenceFactory.processSentence("a=1;");
	}

    @Test
    public void sentenceHasTokens () {
        assertThat(sentence.getNestedSentence(), is(empty()));
    }

    @Test
    public void sentenceHasType () {
        assertThat(sentence.getType(), is(equalTo("")));
    }

    @Test
    public void sentenceHasReturnedType () {
        assertThat(sentence.getReturnedType(), is(equalTo("")));
    }

    @Test
    public void sentenceCanBeAToken () {
        assertTrue(sentence.isToken());
    }
}
