package parser.operators.matchers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SimpleMatcherTest {
    private static final String REGEX_1 = "REGEX_1";
    private static final String REGEX_2 = "REGEX_2";
    private static final String REGEX_3 = "REGEX_3";

    @Test
    public void simpleMatcherHasRegex() {
        SimpleMatcher sp = new SimpleMatcher(REGEX_1);
        assertThat(sp.toString(), is(equalTo(REGEX_1)));
    }

    @Test
    public void simpleMatcherHasRegex_2() {
        SimpleMatcher sp = new SimpleMatcher(REGEX_1, REGEX_2, REGEX_3);
        assertThat(sp.toString(), is(equalTo(REGEX_1 + "|" + REGEX_2 + "|" + REGEX_3)));
    }
}
