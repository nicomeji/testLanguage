package parser.sentence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleSentenceTest {
	private static final String INTEGER = "Integer";
	private static SimpleSentence sentence;
	
	@BeforeClass
	public static void setUp () {
		sentence = new SimpleSentence(INTEGER);
	}
	
    @Test
    public void sentenceHasReturnedType () {
        assertThat(sentence.getReturnedType(), is(equalTo(INTEGER)));
    }
}
