package parser.matchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.management.RuntimeErrorException;

import parser.matchers.CharacterMatcher.MatcherStatus;
import parser.priority.Priority;

public class PriorityMatcher_V2 extends Observable implements Matcher<Character>, Observer {
    private final Priority<CharacterMatcher> priority;
    private List<CharacterMatcher> matchers;

    protected PriorityMatcher_V2(Priority<CharacterMatcher> priority) {
        this.priority = priority;
        reset();
    }

    @Override
    public void parse(Character a) {
        matchers.stream().forEach(matcher -> matcher.parse(a));
    }

    @Override
    public void reset() {
        clearChanged();
        matchers = new ArrayList<CharacterMatcher>();
        matchers.addAll(priority.getOperators());
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (process(o, arg)) {
        case MATCHED:
            setChanged();
            notifyObservers(MatcherStatus.MATCHED);
            break;
        case NOT_MATCHED:
            if (matchers.isEmpty()) {
                setChanged();
                notifyObservers(MatcherStatus.NOT_MATCHED);
            }
            break;
        }
    }

    private MatcherStatus process(Observable o, Object arg) {
        if (matchers.remove(o) && arg instanceof MatcherStatus) {
            return (MatcherStatus) arg;
        }
        throw new RuntimeErrorException(new Error("This is imposible."));
    }
}
