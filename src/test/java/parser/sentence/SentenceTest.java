package parser.sentence;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import parser.sentence.Sentence;

public class SentenceTest {
    private static final String SENTENCE = "a=1;";

    @Test(expected=IllegalArgumentException.class)
    public void sentencesEndWithSemicolon () {
        new Sentence("Something useless.");
    }

    @Test
    public void sentenceHasTokens () {
        assertThat(new Sentence(SENTENCE).getTokens(), is(not(empty())));
    }

    @Test
    public void sentenceHasType () {
        assertThat(new Sentence(SENTENCE).getReturnedType(), is(not(nullValue())));
    }

    @Test
    public void sentenceHasReturnedType () {
        assertThat(new Sentence(SENTENCE).getReturnedType(), is(not(nullValue())));
    }
}
