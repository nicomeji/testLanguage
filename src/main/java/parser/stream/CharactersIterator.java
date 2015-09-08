package parser.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Stream;

public class CharactersIterator implements Iterator<Character> {
    private final Stream<String> source;
    private Iterator<Character> buffer;
    private Stack<Character> aux;

    private CharactersIterator(Stream<String> in) {
        this.source = in;
        this.aux = new Stack<Character>();
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

    public void pushBach(Character a) {
        aux.push(a);
    }

    @Override
    public boolean hasNext() {
        if (!aux.empty()) {
            return true;
        }
        if (buffer.hasNext()) {
            return true;
        }
        updateInternalBuffer();
        return buffer.hasNext();
    }

    @Override
    public Character next() {
        if (!aux.empty()) {
            return aux.pop();
        }
        return buffer.next();
    }
}
