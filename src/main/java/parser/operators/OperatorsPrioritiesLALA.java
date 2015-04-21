package parser.operators;

import java.util.LinkedList;
import java.util.List;

public class OperatorsPrioritiesLALA implements Comparable<OperatorsPrioritiesLALA> {
    private static final OperatorsPrioritiesLALA MIN_VALUE = new OperatorsPrioritiesLALA();
    public static final OperatorsPrioritiesLALA MAX_VALUE = new OperatorsPrioritiesLALA();
    @SuppressWarnings("serial")
    private static final List<OperatorsPrioritiesLALA> PRIORITIES = new LinkedList<OperatorsPrioritiesLALA>() {
        {
            add(MIN_VALUE);
            add(MAX_VALUE);
        }
    };

    private OperatorsPrioritiesLALA() {
    }

    public OperatorsPrioritiesLALA (OperatorsPrioritiesLALA from) {
        OperatorsPrioritiesLALA priority = new OperatorsPrioritiesLALA();
        PRIORITIES.add(from.getValue(), priority);
    }

    public int getValue() {
        return PRIORITIES.indexOf(this);
    }

    @Override
    public int compareTo(OperatorsPrioritiesLALA o) {
        return getValue() - o.getValue();
    }
}
