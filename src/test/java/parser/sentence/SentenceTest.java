package parser.sentence;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import parser.sentence.Sentence;

public class SentenceTest {
	private static final String INTEGER = "Integer";
	private static Sentence sentence;
	
	@BeforeClass
	public static void setUp () {
		sentence = new Sentence(INTEGER, Collections.emptyList());
	}
	
    @Test
    public void sentenceHasTokens () {
        assertThat(sentence.getNestedSentence(), is(empty()));
    }

    @Test
    public void sentenceHasReturnedType () {
        assertThat(sentence.getReturnedType(), is(equalTo(INTEGER)));
    }

    @Test
    public void sentenceCanBeAToken () {
        assertTrue(sentence.isToken());
    }
}
