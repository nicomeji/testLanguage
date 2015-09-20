package parser.matchers;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import parser.priority.Priority;

public class PriorityMatcher extends Observable implements Matcher<Character>, Observer {
    private final Priority<CharacterMatcher> priority;
    private List<CharacterMatcher> matchers;

    protected PriorityMatcher(Priority<CharacterMatcher> priority) {
        this.priority = priority;
        this.priority.getOperators().forEach(operator -> operator.addObserver(this));
        reset();
    }

    @Override
    public void parse(Character a) {
        matchers.forEach(matcher -> matcher.parse(a));
    }

    @Override
    public void reset() {
        clearChanged();
        matchers = priority.getOperators().stream().peek(CharacterMatcher::reset).collect(Collectors.toList());
    }

    @Override
    public void update(Observable o, Object arg) {
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

    private boolean matches(Observable o, Object arg) {
        if (matchers.remove(o)) {
            return (boolean) arg;
        }
        throw new RuntimeErrorException(new Error("This is imposible."));
    }
}
