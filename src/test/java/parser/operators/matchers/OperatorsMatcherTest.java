package parser.operators.matchers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class OperatorsMatcherTest {
    private static final String SIMPLE_WORD = "[a-z]+";
    private static final String SUM = "+";

    @Test
    public void matcherHasMatchingOperator() {
        OperatorsMatcher matcher = new OperatorsMatcher(SUM, 1, new SimpleMatcher(SIMPLE_WORD));
        assertThat(matcher.getSymbol(), is(SUM));
    }

    @Test
    public void matcherHasOperand() {
        OperatorsMatcher matcher = new OperatorsMatcher(SUM, 1, new SimpleMatcher(SIMPLE_WORD));
        assertThat(matcher.getOperands(), is(not(empty())));
        assertThat(matcher.getOperands().size(), is(1));
    }

    @Test
    public void matcherHasOperands() {
        OperatorsMatcher matcher = new OperatorsMatcher(SUM, 1, new SimpleMatcher(SIMPLE_WORD), new SimpleMatcher(
                SIMPLE_WORD), new SimpleMatcher(SIMPLE_WORD));
        assertThat(matcher.getOperands(), is(not(empty())));
        assertThat(matcher.getOperands().size(), is(3));
    }

    @Test
    public void matcherProcessAString() {
        OperatorsMatcher matcher = new OperatorsMatcher(SUM, 1, new SimpleMatcher(SIMPLE_WORD), new SimpleMatcher(
                SIMPLE_WORD));
        assertThat(matcher.getOperands(), is(not(empty())));
        assertThat(matcher.getOperands().size(), is(2));
        List<Integer> indexes = matcher.getIndexes("one +   two");
        assertThat(indexes, is(not(empty())));
        assertThat(indexes.size(), is(1));
        assertThat(indexes, contains(4));
    }

    @Test
    public void matcherProcessAString_2() {
        OperatorsMatcher matcher = new OperatorsMatcher(SUM, 1, new SimpleMatcher(SIMPLE_WORD), new SimpleMatcher(
                SIMPLE_WORD));
        assertThat(matcher.getOperands(), is(not(empty())));
        assertThat(matcher.getOperands().size(), is(2));
        List<Integer> indexes = matcher.getIndexes("one +   two  + three +four");
        assertThat(indexes, is(not(empty())));
        assertThat(indexes.size(), is(4));
        assertThat(indexes.get(0), is(equalTo("one")));
        assertThat(indexes.get(1), is(equalTo("two")));
        assertThat(indexes.get(2), is(equalTo("three")));
        assertThat(indexes.get(3), is(equalTo("four")));
    }
}
