package parser.stream;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MatcherObserver implements Observer {
    public MatcherObserver(List <Matcher> matchers) {
        matchers.stream().forEach(matcher -> {
            matcher.addObserver(this);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    public Key getMatched () {
        return null;
    }
}
