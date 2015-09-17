package parser.priority;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import parser.operators.Operator;

public class PriorityTest {
    @Test
    public void priorityContainsOperators_1() {
        Priority<Operator> priority = new Priority<Operator>(asList(new Operator("Minus", "-", "Any description")));
        assertThat(priority.getOperators(), hasSize(1));
    }

    @Test
    public void priorityContainsOperators_2() {
        Priority<Operator> priority = new Priority<Operator>(
                asList(new Operator("Minus", "-", "Any description"), new Operator("Plus", "+", "Any description")));
        assertThat(priority.getOperators(), hasSize(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void priorityCannotHasDuplicateOperators() {
        new Priority<Operator>(asList(new Operator("Minus", "-", "Any description"), new Operator("Minus", "-", "Any description")));
    }
}
