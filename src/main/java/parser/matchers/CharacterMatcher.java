package parser.matchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import lombok.Getter;
import parser.operators.Key;

public abstract class CharacterMatcher extends Observable {
    public enum MatcherStatus {
        MATCHED, POTENTIAL_MATCHING, NOT_MATCHED;
    }

    protected final List<Character> symbol;

    @Getter
    private final Key key;

    @Getter
    private MatcherStatus status;

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
        status = MatcherStatus.POTENTIAL_MATCHING;
        clearChanged();
    }

    public void parse(Character a) {
        if (status.equals(MatcherStatus.POTENTIAL_MATCHING)) {
            check(a);
        }
    }

    protected abstract void check(Character a);

    protected void matched() {
        setStatus(MatcherStatus.MATCHED);
    }

    protected void notMatched() {
        setStatus(MatcherStatus.NOT_MATCHED);
    }

    private void setStatus(MatcherStatus status) {
        this.status = status;
        setChanged();
        notifyObservers(key);
    }
}
