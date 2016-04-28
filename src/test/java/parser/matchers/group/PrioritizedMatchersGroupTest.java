package parser.matchers.group;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

import org.junit.Before;
import org.junit.Test;

import parser.matchers.Matcher;
import parser.matchers.Matcher.Status;

public class PrioritizedMatchersGroupTest {
    @ToString(callSuper = false, includeFieldNames = false)
    private class TestNumberMatcher implements Matcher<String, Character> {
        private List<Character> auxKey;
        private int index = 0;
        @Getter
        private String key;
        @Getter
        private Status status;

        public TestNumberMatcher(Integer key) {
            this.key = key.toString();
            this.auxKey = new ArrayList<Character>();
            for (char c : key.toString().toCharArray()) {
                this.auxKey.add(new Character(c));
            }
            reset();
        }

        @Override
        public void reset() {
            status = Status.PENDING;
            index = 0;
        }

        @Override
        public void parse(Character a) {
            if (index < auxKey.size() && auxKey.get(index++).equals(a)) {
                if (index == auxKey.size()) {
                    status = Status.MATCHED;
                }
            } else {
                status = Status.NOT_MATCHED;
            }
        }
    }

    private PrioritizedMatchersGroup<String, Character> matcher;

    @Before
    public void setUp() {
        matcher = new PrioritizedMatchersGroup<String, Character>(
                asList(new TestNumberMatcher(12345), new TestNumberMatcher(1324), new TestNumberMatcher(123)));
    }

    @Test
    public void checkMatches() {
        matcher.parse('1');
        matcher.parse('2');
        matcher.parse('3');
        assertThat(matcher.getStatus(), is(equalTo(Status.PENDING)));
        assertThat(matcher.getKey(), is(nullValue()));
        matcher.parse('4');
        matcher.parse('5');
        assertThat(matcher.getStatus(), is(equalTo(Status.MATCHED)));
        assertThat(matcher.getKey(), is(equalTo("12345")));
    }

    @Test
    public void checkNotMatches() {
        matcher.parse('1');
        matcher.parse('2');
        matcher.parse('3');
        assertThat(matcher.getStatus(), is(equalTo(Status.PENDING)));
        assertThat(matcher.getKey(), is(nullValue()));
        matcher.parse('0');
        assertThat(matcher.getStatus(), is(Status.NOT_MATCHED));
        assertThat(matcher.getKey(), is(nullValue()));
    }
}
