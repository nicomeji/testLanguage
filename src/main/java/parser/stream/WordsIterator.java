package parser.stream;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import parser.matchers.Matcher;
import parser.matchers.Matcher.Status;
import parser.matchers.group.MatchersGroup;

public class WordsIterator implements Iterator<String> {
    private final MatchersGroup<String, Character, Collection<Matcher<String, Character>>> matchersGroup;
    private final Iterator<Character> source;

    private WordsIterator(Iterator<Character> in,
            MatchersGroup<String, Character, Collection<Matcher<String, Character>>> matchersGroup) {
        this.source = in;
        this.matchersGroup = matchersGroup;
    }

    public static Stream<String> of(Iterator<Character> in,
            MatchersGroup<String, Character, Collection<Matcher<String, Character>>> matchersGroup) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(new WordsIterator(in, matchersGroup), Spliterator.ORDERED), false);
    }

    @Override
    public boolean hasNext() {
        return Status.MATCHED.equals(matchersGroup.getStatus());
    }

    @Override
    public String next() {
        String retrunValue = matchersGroup.getKey();
        matchersGroup.reset();
        while (source.hasNext() && Status.MATCHED.equals(matchersGroup.getStatus())) {
            matchersGroup.parse(source.next());
        }
        return retrunValue;
    }
}
