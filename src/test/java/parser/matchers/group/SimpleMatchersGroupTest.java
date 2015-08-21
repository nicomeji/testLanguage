package parser.matchers.group;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import lombok.Getter;
import lombok.ToString;

import org.junit.Before;
import org.junit.Test;

import parser.matchers.Matcher;
import parser.matchers.Matcher.Status;

public class SimpleMatchersGroupTest {
    @ToString(callSuper = false, includeFieldNames = false)
    private class TestStringMatcher implements Matcher<String, Character> {
        @Getter
        private String key;
        @Getter
        private Status status;

        private int index;

        public TestStringMatcher(String key) {
            this.key = key;
            reset();
        }

        @Override
        public void reset() {
            status = Status.PENDING;
            index = 0;
        }

        @Override
        public void parse(Character a) {
            if (a != null && a.equals(key.charAt(index++))) {
                if (index == key.length()) {
                    status = Status.MATCHED;
                }
            } else {
                status = Status.NOT_MATCHED;
            }
        }
    }

    private static final String KEY_1 = "abc123";
    private static final String KEY_2 = "abc1234";
    private static final String KEY_3 = "abcdfg";
    private static final String KEY_4 = "123abc";
    private SimpleMatchersGroup<String, Character> matcher;

    private void parse(String string) {
        for (char a : string.toCharArray()) {
            matcher.parse(a);
        }
    }

    @Before
    public void setUp() {
        matcher = new SimpleMatchersGroup<String, Character>(asList(new TestStringMatcher(KEY_1), new TestStringMatcher(KEY_2), new TestStringMatcher(KEY_3),
                new TestStringMatcher(KEY_4)));
    }

    @Test
    public void checkMatches_1() {
        parse(KEY_1);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_1)));
    }

    @Test
    public void checkMatches_2() {
        parse(KEY_3);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_3)));
    }

    @Test
    public void checkMatches_3() {
        parse(KEY_4);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_4)));
    }

    /**
     * It's important note that while parsing the char sequence "abc1234" the
     * first match will be "abc123", which is the actual case.
     * 
     * But this result is expected, and it's correct. We are working with a
     * character matcher, it processes each letter at a time. The objective of
     * this matcher is match special patterns in each word, like:
     * 
     * price-discount
     * 
     * This matcher should be able of recognize the minus sign in the middle of the
     * string. Other matcher, for example a word matcher, won't be able of math
     * this.
     */
    @Test
    public void checkMatches_4() {
        parse(KEY_2);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_1)));
    }

    @Test
    public void checkReset_1() {
        parse("lala");
        assertThat(matcher.getStatus(), is(Status.NOT_MATCHED));
        assertThat(matcher.getKey(), is(nullValue()));
        matcher.reset();
        parse(KEY_1);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_1)));
    }

    @Test
    public void checkReset_2() {
        parse(KEY_2);
        matcher.reset();
        parse(KEY_1);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_1)));
    }

    @Test
    public void checkReset_3() {
        parse(KEY_1);
        matcher.reset();
        parse(KEY_3);
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo(KEY_3)));
    }

    @Test
    public void checkPending_1() {
        matcher.parse('a');
        matcher.parse('b');
        matcher.parse('c');
        assertThat(matcher.getStatus(), is(equalTo(Status.PENDING)));
        assertThat(matcher.getKey(), is(nullValue()));
        matcher.parse('z');
        assertThat(matcher.getStatus(), is(equalTo(Status.NOT_MATCHED)));
        assertThat(matcher.getKey(), is(nullValue()));
    }

    @Test
    public void checkPending_2() {
        matcher.parse('a');
        assertThat(matcher.getStatus(), is(equalTo(Status.PENDING)));
        assertThat(matcher.getKey(), is(nullValue()));
        matcher.reset();
        matcher.parse('a');
        assertThat(matcher.getStatus(), is(equalTo(Status.PENDING)));
        assertThat(matcher.getKey(), is(nullValue()));
    }

    @Test
    public void checkPending_3() {
        matcher.parse('z');
        assertThat(matcher.getStatus(), is(equalTo(Status.NOT_MATCHED)));
        assertThat(matcher.getKey(), is(nullValue()));
        matcher.reset();
        matcher.parse('a');
        assertThat(matcher.getStatus(), is(equalTo(Status.PENDING)));
        assertThat(matcher.getKey(), is(nullValue()));
    }
}
