package parser.matchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import lombok.Getter;
import parser.operators.Key;

public abstract class CharacterMatcher extends Observable {

    protected final List<Character> symbol;

    @Getter
    private final Key key;

    public CharacterMatcher(String symbol) {
        List<Character> aux = new ArrayList<Character>();
        for (Character character : symbol.toCharArray()) {
            aux.add(character);
        }
        this.symbol = Collections.unmodifiableList(aux);
        key = new Key(symbol);
        reset();
    }

    public void reset() {
        clearChanged();
    }

    public abstract void parse(Character a);

    protected void matched() {
        setChanged();
        notifyObservers(true);
    }

    protected void notMatched() {
        setChanged();
        notifyObservers(false);
    }
}
