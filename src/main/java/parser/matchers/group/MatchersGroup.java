package parser.matchers.group;

import java.util.Collection;
import java.util.stream.Collector;

import lombok.Getter;
import parser.matchers.Matcher;

public abstract class MatchersGroup<VALUE, ELEMENT, COLLECTION extends Collection<Matcher<VALUE, ELEMENT>>>
        implements Matcher<VALUE, ELEMENT> {
    private final Collector<Matcher<VALUE, ELEMENT>, ?, COLLECTION> collector;
    private final COLLECTION matchers;
    private COLLECTION pending;
    @Getter
    protected Status status;
    @Getter
    protected VALUE key;

    public MatchersGroup(COLLECTION matchers, Collector<Matcher<VALUE, ELEMENT>, ?, COLLECTION> collector) {
        this.collector = collector;
        this.matchers = matchers;
        reset();
    }

    @Override
    public void reset() {
        pending = matchers.parallelStream().peek(Matcher::reset).collect(collector);
        status = Status.PENDING;
        key = null;
    }

    @Override
    public void parse(ELEMENT a) {
        pending = pending.parallelStream().peek(matcher -> matcher.parse(a)).filter(this::rmNotMatched)
                .collect(collector);
        if (pending.isEmpty()) {
            status = Status.NOT_MATCHED;
        } else {
            updateKey(pending);
        }
    }

    protected abstract void updateKey(COLLECTION metchersList);

    private boolean rmNotMatched(Matcher<VALUE, ELEMENT> matcher) {
        return !Status.NOT_MATCHED.equals(matcher.getStatus());
    }
}
