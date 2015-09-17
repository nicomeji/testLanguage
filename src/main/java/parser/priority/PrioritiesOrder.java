package parser.priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class PrioritiesOrder<K extends Priority<T>, T> {
    private final List<K> priorities;

    protected PrioritiesOrder(List<K> piorities) {
        List<K> priorityAux = new ArrayList<K>();
        for (K priority : piorities) {
            if (priorityAux.contains(priority)) {
                throw new IllegalArgumentException();
            }
            priorityAux.add(priority);
        }
        this.priorities = Collections.unmodifiableList(priorityAux);
    }

    public static <N extends Priority<M>, M> PrioritiesOrder<N, M> of(List<N> piorities) {
        return new PrioritiesOrder<N, M>(piorities);
    }
}
