package parser.matchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import lombok.Getter;
import parser.matchers.CharacterMatcher.MatcherStatus;
import parser.operators.Key;
import parser.priority.Priority;

public class PriorityMatcher extends Observable implements Matcher<Character>, Observer {
    @Getter
    private class SimpleEntry {
        private final MatcherStatus status;
        private final Key key;

        public SimpleEntry(CharacterMatcher matcher) {
            this.status = matcher.getStatus();
            this.key = matcher.getKey();
        }
    }

    private Map<MatcherStatus, List<Key>> keysMap;
    private final Map<MatcherStatus, List<CharacterMatcher>> matchersMap;
    private MatcherStatus groupStatus;

    protected PriorityMatcher(Priority<CharacterMatcher> priority) {
        groupStatus = MatcherStatus.POTENTIAL_MATCHING;
        matchersMap = priority.getOperators().stream().collect(Collectors.groupingBy(CharacterMatcher::getStatus, HashMap::new, Collectors.toCollection(ArrayList::new)));
        keysMap = priority.getOperators().stream().map(SimpleEntry::new)
                .collect(Collectors.groupingBy(SimpleEntry::getStatus, HashMap::new, Collectors.mapping(SimpleEntry::getKey, Collectors.toCollection(ArrayList::new))));
    }

    @Override
    public void parse(Character a) {
        if (MatcherStatus.POTENTIAL_MATCHING.equals(groupStatus)) {
            matchersMap.get(MatcherStatus.POTENTIAL_MATCHING).stream().forEach(operator -> {
                operator.parse(a);
            });
        }
    }

    @Override
    public void reset() {
        clearChanged();
        groupStatus = MatcherStatus.POTENTIAL_MATCHING;
        matchersMap.entrySet().stream().map(Entry::getValue).forEach(matcherList -> {
            matcherList.stream().forEach(CharacterMatcher::reset);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        CharacterMatcher matcher = null;
        if (o instanceof CharacterMatcher) {
            matcher = (CharacterMatcher) o;
        } else {
            throw new RuntimeErrorException(new Error("This is imposible."));
        }
        if (MatcherStatus.MATCHED.equals(matcher.getStatus())) {
            groupStatus = MatcherStatus.MATCHED;
            setChanged();
            notifyObservers(matcher.getKey());
        } else {
            List<CharacterMatcher> matchers = matchersMap.get(MatcherStatus.POTENTIAL_MATCHING);
            if (matchers.isEmpty()) {
                groupStatus = MatcherStatus.NOT_MATCHED;
                setChanged();
                notifyObservers(null);
            } else {
                matchers.remove(matcher);
                // Ver los nulls Hay muchos-
            }
        }
    }
}

