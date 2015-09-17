package parser.priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import parser.matchers.CharacterMatcher;
import lombok.Data;
import lombok.Getter;

@Data
public class OperatorsPriorities<T> {
    private final List<Priority<T>> priorities;
    private final Set<T> operators;

    public OperatorsPriorities(List<Priority<T>> piorities) {
        List<Priority<T>> aux = new ArrayList<Priority<T>>();
        operators = new HashSet<T>();
        for (Priority<T> priority : piorities) {
            priority.getOperators().stream().forEach(operator -> {
                if (!operators.add(operator)) {
                    throw new IllegalArgumentException();
                }
            });
            if (!aux.add(priority)) {
                throw new IllegalArgumentException();
            }
        }
        this.priorities = Collections.unmodifiableList(aux);
    }

    public Stream<Priority<T>> stream() {
        return priorities.stream();
    }
}
