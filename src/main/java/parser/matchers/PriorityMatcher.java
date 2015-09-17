package parser.matchers;

import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import parser.matchers.CharacterMatcher.MatcherStatus;
import parser.priority.Priority;

@Data
@EqualsAndHashCode(callSuper = true)
public class PriorityMatcher extends Observable implements Matcher<Character>, Observer {
    private final Collection<CharacterMatcher> lala;
    private MatcherStatus groupStatus;

    protected PriorityMatcher(Priority<CharacterMatcher> priority) {
        groupStatus = MatcherStatus.POTENTIAL_MATCHING;
        lala = priority.getOperators();
    }

    @Override
    public void parse(Character a) {
        lala.stream().peek(operator -> {
            operator.parse(a);
        });
    }

    @Override
    public void reset() {
        groupStatus = MatcherStatus.POTENTIAL_MATCHING;
        lala.stream().forEach(CharacterMatcher::reset);
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
