package parser.matchers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import parser.priority.PrioritiesOrder;
import parser.priority.Priority;

// TODO - Chequeo q un matcher es valido.
// Si todos los demas q son mas prioritarios no matchean, entonces es el correcto.
// Pero si alguno más prioritario todavia puede matchear, hay q seguir.

public class MatcherObserver extends Observable implements Observer {
    // TODO - esta debería ser una lista de priorities.
    // Cada priority contiene un matcher.
    private final PrioritiesOrder<Priority<CharacterMatcher>, CharacterMatcher> priorityMatchers;
    private List<PriorityMatcher> matchers;

    public MatcherObserver(PrioritiesOrder<Priority<CharacterMatcher>, CharacterMatcher> priorityMatchers) {
        this.priorityMatchers = priorityMatchers;
        reset();
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO - Chequeo q un matcher es valido.
        // Si todos los demas q son mas prioritarios no matchean, entonces es el
        // correcto.
        // Pero si alguno más prioritario todavia puede matchear, hay q seguir.

        if (matches(o, arg)) {
            setChanged();
            notifyObservers(true);
        } else {
            if (matchers.isEmpty()) {
                setChanged();
                notifyObservers(false);
            }
        }
    }

    public void parse(Character a) {
        matchers.forEach(priorityMatcher -> priorityMatcher.parse(a));
    }

    public void reset() {
        // TODO - esto se llama cuando ya se matcheo lo que se buscaba.
        // O cuando se termina de parsear una palabra completa y no se matcheo
        // nada.
        // Aclaracion: No siempre todo es una key, el nombre de una variable es
        // una palabra simple.
        matchers = priorityMatchers.getPriorities().stream().map(PriorityMatcher::new).collect(Collectors.toList());
        matchers.forEach(PriorityMatcher::reset);
    }

    private boolean matches(Observable o, Object arg) {
        boolean matches = (boolean) arg;
        if (matchers.get(0).matches()) {
            
        }
        if (matchers.indexOf(o) == 0) {
            return matches;
        }
        throw new RuntimeErrorException(new Error("This is imposible."));
    }

}
