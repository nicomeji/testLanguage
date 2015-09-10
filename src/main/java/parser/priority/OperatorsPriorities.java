package parser.priority;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OperatorsPriorities <T> {
    public final Priority<T> MIN_VALUE = new Priority<T>();
    public final Priority<T> MAX_VALUE = new Priority<T>();
    private List<Priority<T>> priorities;

    public OperatorsPriorities() {
        priorities = new LinkedList<Priority<T>>();
        priorities.add(MIN_VALUE);
        priorities.add(MAX_VALUE);
    }

    public Priority<T> insert(Priority<T> reference, Priority<T> newPriority) {
        int index;
        if (MIN_VALUE == reference) {
            index = 1;
        } else {
            index = priorities.indexOf(reference);
        }
        priorities.add(index, newPriority);
        return newPriority;
    }

    public List<Priority<T>> getPriorities() {
        return Collections.unmodifiableList(priorities);
    }
}
