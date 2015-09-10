package parser.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CharactersIterator implements Iterator<Character> {
    private final Stream<String> source;
    private Iterator<Character> buffer;

    private CharactersIterator(Stream<String> in) {
        this.source = in;
    }

    // public static Stream<Character> of (Stream<String> in) {
    public static CharactersIterator of(Stream<String> in) {
        return new CharactersIterator(in);
    }

    private void updateInternalBuffer() {
        Optional<String> line = source.findFirst();
        if (line.isPresent()) {
            List<Character> items = new ArrayList<Character>();
            for (char item : line.get().toCharArray()) {
                items.add(item);
            }
            buffer = items.iterator();
        }
    }

    @Override
    public boolean hasNext() {
        if (buffer.hasNext()) {
            return true;
        }
        updateInternalBuffer();
        return buffer.hasNext();
    }

    @Override
    public Character next() {
        return buffer.next();
    }
}
