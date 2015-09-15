package parser.priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class OperatorsPriorities <T> {
    public final Priority<T> MIN_VALUE;
    public final Priority<T> MAX_VALUE;
    private List<Priority<T>> priorities;

    public OperatorsPriorities(Priority<T> min, Priority<T> max) {
        priorities = new ArrayList<Priority<T>>();
        priorities.add(MIN_VALUE = min);
        priorities.add(MAX_VALUE = max);
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

    public Stream<Priority<T>> stream() {
        return Collections.unmodifiableList(priorities).stream();
    }
}
