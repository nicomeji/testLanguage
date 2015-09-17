package parser.priority;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

/**
 * Important: This class has a particular implementation of the equals method.
 * Which means that you can have two priorities with different hashCode but both
 * are considered equals, which means it shouldn't be used for HashSet or
 * HashMap key.
 */
public class Priority<T> {
    @Getter
    private final Set<T> operators;

    protected Priority(List<T> operators) {
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

    public static <E> Priority<E> of(List<E> operators) {
        return new Priority<E>(operators);
    }

    /**
     * Two priorities are equal if they have at least one operator in common. I
     * know this is not the equal strict meaning, but it isn't acceptable have
     * same operator with two different priorities. That scenario can cause many
     * parsing problems. So, this is a small hack. We assume that two priorities
     * are different if they don't have any operator in common; otherwise, if
     * the same operator is present in both priorities (and we know that every
     * operator has one, and only one, priority), then we must assume that both
     * priorities are equal.
     * 
     * For example: If one priority has operators "==" and "!=", and other
     * priority has "==" and "===", then we conclude that "===" and "!=" have
     * the same priority too.
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object o) {
        if (o instanceof Priority) {
            return !Collections.disjoint(operators, ((Priority) o).operators);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return operators.hashCode();
    }
}
