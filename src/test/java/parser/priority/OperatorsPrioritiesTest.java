package parser.priority;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import parser.operators.Operator;

public class OperatorsPrioritiesTest {
    OperatorsPriorities<Operator> operationsPriorities;

    @Before
    public void setUp() {
        operationsPriorities = new OperatorsPriorities<Operator>();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void priorityListIsReadable() {
        assertThat(operationsPriorities.getPriorities(), containsInAnyOrder(operationsPriorities.MIN_VALUE, operationsPriorities.MAX_VALUE));

        Priority<Operator> newPriority = new Priority<Operator>();
        operationsPriorities.insert(operationsPriorities.MIN_VALUE, newPriority);
        assertThat(operationsPriorities.getPriorities(), containsInAnyOrder(operationsPriorities.MIN_VALUE, newPriority, operationsPriorities.MAX_VALUE));
    }

    @Test
    public void minPriorityMustAlwaysbeMin() {
        Priority<Operator> newPriority = new Priority<Operator>();
        operationsPriorities.insert(operationsPriorities.MIN_VALUE, newPriority);
        List<Priority<Operator>> priorities = operationsPriorities.getPriorities();
        assertThat(priorities.get(0), is(equalTo(operationsPriorities.MIN_VALUE)));
    }

    @Test
    public void maxPriorityMustAlwaysbeMax() {
        Priority<Operator> newPriority = new Priority<Operator>();
        operationsPriorities.insert(operationsPriorities.MIN_VALUE, newPriority);
        List<Priority<Operator>> priorities = operationsPriorities.getPriorities();
        assertThat(priorities.get(priorities.size() - 1), is(equalTo(operationsPriorities.MAX_VALUE)));
    }
}
