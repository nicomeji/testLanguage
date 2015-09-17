package parser.priority;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import parser.operators.Operator;

public class PriorityTest {
    private static final Operator PLUS = new Operator("Plus", "+", "Any description");
    private static final Operator MINUS = new Operator("Minus", "-", "Any description");
    private static final Operator EQUAL = new Operator("Euqal", "=", "Any description");

    @Test
    public void priorityContainsOperators_1() {
        Priority<Operator> priority = Priority.of(asList(MINUS));
        assertThat(priority.getOperators(), hasSize(1));
    }

    @Test
    public void priorityContainsOperators_2() {
        Priority<Operator> priority = Priority.of(asList(MINUS, PLUS));
        assertThat(priority.getOperators(), hasSize(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void priorityCannotHasDuplicateOperators() {
        Priority.of(asList(MINUS, EQUAL, MINUS));
    }
}
