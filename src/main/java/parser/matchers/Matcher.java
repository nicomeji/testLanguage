package parser.matchers;

import java.util.Observer;

public interface Matcher<T> {
    public void parse(T a);

    public void reset();

    public void addObserver(Observer o);
}
