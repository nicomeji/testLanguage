package parser.priority;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import parser.operators.Operator;

public class OperatorsPrioritiesTest {
    private PrioritiesOrder<Priority<Operator>, Operator> operationsPriorities;
    private static final Priority<Operator> PLUS_PRIORITY = Priority.of(asList(new Operator("Plus", "+", "Any description")));
    private static final Priority<Operator> MINUS_PRIORITY = Priority.of(asList(new Operator("Minus", "-", "Any description")));
    private static final Priority<Operator> EQUAL_PRIORITY = Priority.of(asList(new Operator("Euqal", "=", "Any description")));

    @Test
    public void priorityListIsReadable_1() {
        operationsPriorities = PrioritiesOrder.of(asList(EQUAL_PRIORITY, PLUS_PRIORITY));

        assertThat(operationsPriorities.getPriorities(), hasSize(2));
    }

    @Test
    public void priorityListIsReadable_2() {
        operationsPriorities = PrioritiesOrder.of(asList(EQUAL_PRIORITY, MINUS_PRIORITY, PLUS_PRIORITY));

        assertThat(operationsPriorities.getPriorities(), hasSize(3));
    }

    @Test
    public void priorityListKeepOrder() {
        operationsPriorities = PrioritiesOrder.of(asList(EQUAL_PRIORITY, MINUS_PRIORITY, PLUS_PRIORITY));

        assertThat(operationsPriorities.getPriorities(), hasSize(3));
        assertThat(operationsPriorities.getPriorities().get(0), is(EQUAL_PRIORITY));
        assertThat(operationsPriorities.getPriorities().get(1), is(MINUS_PRIORITY));
        assertThat(operationsPriorities.getPriorities().get(2), is(PLUS_PRIORITY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAcceptDuplicatePriorities_1() {
        PrioritiesOrder.of(asList(EQUAL_PRIORITY, EQUAL_PRIORITY, PLUS_PRIORITY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAcceptDuplicatePriorities_2() {
        PrioritiesOrder.of(asList(EQUAL_PRIORITY, MINUS_PRIORITY, PLUS_PRIORITY, EQUAL_PRIORITY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAcceptDuplicateOperators() {
        Priority<Operator> p1 = Priority.of(asList(new Operator("Euqal", "=", "Any description"), new Operator("Minus", "-", "Any description")));
        Priority<Operator> p2 = Priority.of(asList(new Operator("Minus", "-", "Any description"), new Operator("Plus", "+", "Any description")));
        PrioritiesOrder.of(asList(p1, p2));
    }
}
