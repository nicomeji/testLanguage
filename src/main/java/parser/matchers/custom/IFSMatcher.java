package parser.matchers.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import parser.matchers.CharacterMatcher.MatcherStatus;
import parser.operators.Key;
import lombok.Getter;

public class IFSMatcher extends Observable {
    private final List<Character> symbol;
    private MatcherStatus notifyOnStatus;
    private int index;

    @Getter
    private Key key;

    @Getter
    private MatcherStatus status;

    public IFSMatcher(String symbol) {
        List<Character> aux = new ArrayList<Character>();
        for (Character character : symbol.toCharArray()) {
            this.symbol.add(character);
        }
        this.symbol = Collections.unmodifiableList(aux);
        this.key = new Key(symbol);
        reset();
    }

    public void reset() {
        this.status = MatcherStatus.POTENTIAL_MATCHING;
        this.index = 0;
        clearChanged();
    }

    public void parse(Character a) {
        if (status.equals(MatcherStatus.POTENTIAL_MATCHING) && index < symbol.size()) {
            check(a);
        }
    }

    private void check (Character a) {
        if (symbol.get(index).equals(a)) {
            if (index == symbol.size() - 1) {
                setStatus(MatcherStatus.MATCHED);
            }
            index++;
        } else {
            setStatus(MatcherStatus.NOT_MATCHED);
        }
    }

    private void setStatus(MatcherStatus status) {
        if (this.status != status) {
            this.status = status;
            setChanged();
            notifyIfNeeded();
        }
    }

    private void notifyIfNeeded() {
        if (notifyOnStatus.equals(status)) {
            notifyObservers(status);
        }
    }
}