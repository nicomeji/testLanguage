package parser.matchers.group;

import java.util.List;
import java.util.stream.Collectors;

import parser.matchers.Matcher;

public class PrioritizedMatchersGroup<VALUE, ELEMENT> extends MatchersGroup<VALUE, ELEMENT, List<Matcher<VALUE, ELEMENT>>> {
    public PrioritizedMatchersGroup(List<Matcher<VALUE, ELEMENT>> matchers) {
        super(matchers, Collectors.toList());
    }

    @Override
    protected void updateKey(List<Matcher<VALUE, ELEMENT>> metchersList) {
        if (Status.MATCHED.equals(metchersList.get(0).getStatus())) {
            key = metchersList.get(0).getKey();
            status = Status.MATCHED;
        }
    }
}
