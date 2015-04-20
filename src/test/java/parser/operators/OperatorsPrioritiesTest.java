package parser.operators;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class OperatorsPrioritiesTest {
    OperatorsPriorities operationsPriorities;

    @Test
    public void priorityListIsReadable() {
        operationsPriorities = new OperatorsPriorities();
        assertThat(
                operationsPriorities.getPriorities(),
                containsInAnyOrder(OperatorsPriorities.MIN_VALUE,
                                   OperatorsPriorities.MAX_VALUE));

        Priority newPriority = new Priority();
        operationsPriorities.insert(OperatorsPriorities.MIN_VALUE, newPriority);
        assertThat(
                operationsPriorities.getPriorities(),
                containsInAnyOrder(OperatorsPriorities.MIN_VALUE,
                                   newPriority,
                                   OperatorsPriorities.MAX_VALUE));
    }

    @Test
    public void minPriorityMustAlwaysbeMin() {
        Priority newPriority = new Priority();
        operationsPriorities = new OperatorsPriorities();
        operationsPriorities.insert(OperatorsPriorities.MIN_VALUE, newPriority);
        List<Priority> priorities = operationsPriorities.getPriorities();
        assertTrue(priorities.get(0) == OperatorsPriorities.MIN_VALUE);
        assertThat(priorities.get(0),
                is(equalTo(OperatorsPriorities.MIN_VALUE)));
    }

    @Test
    public void maxPriorityMustAlwaysbeMax() {
        Priority newPriority = new Priority();
        operationsPriorities = new OperatorsPriorities();
        operationsPriorities.insert(OperatorsPriorities.MIN_VALUE, newPriority);
        List<Priority> priorities = operationsPriorities.getPriorities();
        assertTrue(priorities.get(priorities.size() - 1) == OperatorsPriorities.MAX_VALUE);
        assertThat(priorities.get(priorities.size() - 1),
                is(equalTo(OperatorsPriorities.MAX_VALUE)));
    }
}
