package parser.operators;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class OperatorsPrioritiesTest {
    OperatorsPriorities operationsPriorities;

    @Test
    public void priorityListIsReadable() {
        operationsPriorities = new OperatorsPriorities();
        List<Priority> priorities = operationsPriorities.getPriorities();
        assertTrue(priorities.contains(OperatorsPriorities.MAX_VALUE));
        assertTrue(priorities.contains(OperatorsPriorities.MIN_VALUE));
    }

    @Test
    public void canAddNewPriority() {
        Priority newPriority = new Priority();
        operationsPriorities = new OperatorsPriorities();
        operationsPriorities.insert(OperatorsPriorities.MIN_VALUE, newPriority);
        List<Priority> priorities = operationsPriorities.getPriorities();
        assertTrue(priorities.contains(newPriority));
        assertTrue(priorities.contains(OperatorsPriorities.MAX_VALUE));
        assertTrue(priorities.contains(OperatorsPriorities.MIN_VALUE));
    }

    @Test
    public void minPriorityMustAlwaysbeMin() {
        Priority newPriority = new Priority();
        operationsPriorities = new OperatorsPriorities();
        operationsPriorities.insert(OperatorsPriorities.MIN_VALUE, newPriority);
        List<Priority> priorities = operationsPriorities.getPriorities();
        assertTrue(priorities.get(0) == OperatorsPriorities.MIN_VALUE);
    }

    @Test
    public void maxPriorityMustAlwaysbeMax() {
        Priority newPriority = new Priority();
        operationsPriorities = new OperatorsPriorities();
        operationsPriorities.insert(OperatorsPriorities.MIN_VALUE, newPriority);
        List<Priority> priorities = operationsPriorities.getPriorities();
        assertTrue(priorities.get(priorities.size() - 1) == OperatorsPriorities.MAX_VALUE);
    }
}
