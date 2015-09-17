package parser.priority;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

public class Priority<T> {
    @Getter
    private final Set<T> operators;

    public Priority(List<T> operators) {
        if (operators == null || operators.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Set<T> aux = new HashSet<T>();
        for (T operator : operators) {
            if (!aux.add(operator)) {
                throw new IllegalArgumentException();
            }
        }
        this.operators = Collections.unmodifiableSet(aux);
    }
}
