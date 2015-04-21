package parser.operators;

import java.util.LinkedList;
import java.util.List;

public class Priority implements Comparable<Priority> {
    private static final Priority MIN_VALUE = new Priority();
    private static final Priority MAX_VALUE = new Priority();
    @SuppressWarnings("serial")
    private static final List<Priority> PRIORITIES = new LinkedList<Priority>() {
        {
            add(MIN_VALUE);
            add(MAX_VALUE);
        }
    };

    private Priority() {
    }

    public static Priority createLowest() {
        Priority priority = new Priority();
        PRIORITIES.add(1, priority);
        return priority;
    }

    public static Priority createHighest() {
        Priority priority = new Priority();
        PRIORITIES.add(PRIORITIES.indexOf(MAX_VALUE), priority);
        return priority;
    }

    public static Priority createLowerThan(Priority lessThan) {
        Priority priority = new Priority();
        PRIORITIES.add(PRIORITIES.indexOf(lessThan), priority);
        return priority;
    }

    public int getValue() {
        return PRIORITIES.indexOf(this);
    }

    @Override
    public int compareTo(Priority o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
