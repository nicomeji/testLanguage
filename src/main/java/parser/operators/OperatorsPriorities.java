package parser.operators;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class OperatorsPriorities {
    public static final Priority MIN_VALUE = new Priority();
    public static final Priority MAX_VALUE = new Priority();
    private List<Priority> priorities;

    public OperatorsPriorities() {
        priorities = new LinkedList<Priority>();
        priorities.add(MIN_VALUE);
        priorities.add(MAX_VALUE);
    }

    public Priority insert(Priority reference, Priority newPriority) {
        int index;
        if(MIN_VALUE == reference) {
            index=1;
        } else {
            index = priorities.indexOf(reference);
        }
        priorities.add(index, newPriority);
        return newPriority;
    }

    public List<Priority> getPriorities() {
        return Collections.unmodifiableList(priorities);
    }
}
