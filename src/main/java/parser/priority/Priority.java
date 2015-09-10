package parser.priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Priority<T> {
    private List<T> operators;

    public Priority() {
        operators = new ArrayList<T>();
    }

    public List<T> getOperators() {
        return Collections.unmodifiableList(operators);
    }
}
