package parser.matchers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MatcherObserver implements Observer {
    // TODO - esta debería ser una lista de priorities.
    // Cada priority contiene un matcher.
    private List<CharacterMatcher> matchers;

    public MatcherObserver(List<CharacterMatcher> matchers) {
        this.matchers = matchers;
        this.matchers.stream().forEach(matcher -> {
            matcher.addObserver(this);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO - Chequeo q un matcher es valido.
        // Si todos los demas q son mas prioritarios no matchean, entonces es el correcto.
        // Pero si alguno más prioritario todavia puede matchear, hay q seguir.
    }

    public void parse(Character a) {
        this.matchers.stream().forEach(matcher -> {
            matcher.parse(a);
        });
    }

    public void reset() {
        // TODO - esto se llama cuando ya se matcheo lo que se buscaba.
        // O cuando se termina de parsear una palabra completa y no se matcheo nada.
        // Aclaracion: No siempre todo es una key, el nombre de una variable es una palabra simple.
        this.matchers.stream().forEach(CharacterMatcher::reset);
    }

    public Key getMatched() {
        return null;
    }
}
