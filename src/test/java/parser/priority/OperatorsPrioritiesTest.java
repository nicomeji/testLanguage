package parser.priority;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import parser.operators.Operator;

public class OperatorsPrioritiesTest {
    private OperatorsPriorities<Operator> operationsPriorities;
    private static final Operator PLUS = new Operator("Plus", "+", "Any description");
    private static final Operator MINUS = new Operator("Minus", "-", "Any description");
    private static final Operator EQUAL = new Operator("Euqal", "=", "Any description");

    @Test
    public void priorityListIsReadable_1() {
        operationsPriorities = new OperatorsPriorities<Operator>(asList(new Priority<Operator>(asList(EQUAL)), new Priority<Operator>(asList(PLUS))));

        assertThat(operationsPriorities.getPriorities(), hasSize(2));
    }

    @Test
    public void priorityListIsReadable_2() {
        operationsPriorities = new OperatorsPriorities<Operator>(asList(new Priority<Operator>(asList(EQUAL)),new Priority<Operator>(asList(MINUS)), new Priority<Operator>(asList(PLUS))));

        assertThat(operationsPriorities.getPriorities(), hasSize(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAcceptDuplicateOperators() {
        operationsPriorities = new OperatorsPriorities<Operator>(asList(new Priority<Operator>(asList(EQUAL)),new Priority<Operator>(asList(EQUAL)), new Priority<Operator>(asList(PLUS))));
    }
}
