package parser.sentence;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import parser.sentence.Token;

public class TokenTest {
    private Token token = new Token("type", "returnedType");

    @Test
    public void tokenHasType () {
        assertThat(token.getReturnedType(), is(not(nullValue())));
    }

    @Test
    public void tokenHasReturnedType () {
        assertThat(token.getReturnedType(), is(not(nullValue())));
    }
}
