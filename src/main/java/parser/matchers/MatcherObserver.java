package parser.matchers;

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import parser.matchers.CharacterMatcher.MatcherStatus;
import parser.operators.Key;
import parser.priority.PrioritiesOrder;
import parser.priority.Priority;

// TODO - Chequeo q un matcher es valido.
// Si todos los demas q son mas prioritarios no matchean, entonces es el correcto.
// Pero si alguno más prioritario todavia puede matchear, hay q seguir.

public class MatcherObserver implements Observer {
    @Data
    private class KeyWithFlags {
        private final Key key;
        private MatcherStatus status;

        public KeyWithFlags(Key key) {
            this.key = key;
            status = MatcherStatus.POTENTIAL_MATCHING;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    private class PriorityWithFlag extends Priority<KeyWithFlags> {
        private MatcherStatus groupStatus;

        public PriorityWithFlag(List<Key> keys) {
            super(keys.stream().map(KeyWithFlags::new).collect(Collectors.toList()));
            groupStatus = MatcherStatus.POTENTIAL_MATCHING;
        }
    };

    private class CustomOperatorsPriorities extends PrioritiesOrder<Priority<CharacterMatcher>,CharacterMatcher> {
        protected CustomOperatorsPriorities(List<Priority<CharacterMatcher>> piorities) {
            super(piorities);
        }

        public void parse (Character a) {
        }
    }

    // TODO - esta debería ser una lista de priorities.
    // Cada priority contiene un matcher.
    private PrioritiesOrder<Priority<Key>, Key> priorityMatchers;
    private Set<CharacterMatcher> matchers;

    public MatcherObserver(PrioritiesOrder<Priority<CharacterMatcher>, CharacterMatcher> priorityMatchers) {
        matchers = new HashSet<CharacterMatcher>();
        this.priorityMatchers = PrioritiesOrder.of(priorityMatchers.getPriorities().stream().map(priority -> {
            matchers.addAll(priority.getOperators());
            return Priority.of(priority.getOperators().stream().map(CharacterMatcher::getKey).collect(Collectors.toList()));
        }).collect(Collectors.toList()));
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO - Chequeo q un matcher es valido.
        // Si todos los demas q son mas prioritarios no matchean, entonces es el
        // correcto.
        // Pero si alguno más prioritario todavia puede matchear, hay q seguir.

    }

    public void parse(Character a) {
        matchers.stream().forEach(matcher -> {
            matcher.parse(a);
        });
    }

    public void reset() {
        // TODO - esto se llama cuando ya se matcheo lo que se buscaba.
        // O cuando se termina de parsear una palabra completa y no se matcheo
        // nada.
        // Aclaracion: No siempre todo es una key, el nombre de una variable es
        // una palabra simple.
        matchers.stream().forEach(CharacterMatcher::reset);
    }

    public Key getMatched() {
        return null;
    }
}
