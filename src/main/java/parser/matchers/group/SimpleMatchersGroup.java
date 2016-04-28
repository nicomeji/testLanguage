package parser.matchers.group;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import parser.matchers.Matcher;

public class SimpleMatchersGroup<VALUE, ELEMENT> extends MatchersGroup<VALUE, ELEMENT, List<Matcher<VALUE, ELEMENT>>> {
    public SimpleMatchersGroup(List<Matcher<VALUE, ELEMENT>> matchers) {
        super(matchers, Collectors.toList());
    }

    @Override
    public void parse(ELEMENT a) {
        if (Status.PENDING.equals(status)) {
            super.parse(a);
        }
    }

    @Override
    protected void updateKey(List<Matcher<VALUE, ELEMENT>> metchersList) {
        Optional<Matcher<VALUE, ELEMENT>> matcher = metchersList.stream().filter(this::matches).findFirst();
        if (matcher.isPresent()) {
            key = matcher.get().getKey();
            status = Status.MATCHED;
        }
    }

    private boolean matches(Matcher<VALUE, ELEMENT> matcher) {
        return Status.MATCHED.equals(matcher.getStatus());
    }
}
