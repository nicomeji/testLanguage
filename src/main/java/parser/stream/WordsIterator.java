package parser.stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class WordsIterator implements Iterator<String> {
    private final CharactersIterator source;
    private Iterator<String> buffer;
    private Stack<String> aux;

    private WordsIterator(CharactersIterator in) {
        this.source = in;
        this.aux = new Stack<String>();
    }

    // public static Stream<String> of (Stream<Character> in) {
    public static WordsIterator of(CharactersIterator in) {
        return new WordsIterator(in);
    }

    private void updateInternalBuffer() {
        Optional<String> line = source.findFirst();
        if (line.isPresent()) {
            List<String> items = new ArrayList<String>();
            for (String item : line.get().toCharArray()) {
                items.add(item);
            }
            buffer = items.iterator();
        }
    }

    public void pushBach(String a) {
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
    public String next() {
        if (!aux.empty()) {
            return aux.pop();
        }
        return buffer.next();
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (Scanner s2 = new Scanner("int a=1; long b = 2; float hola_mund=1.0 ;");) {
            while (s2.hasNext()) {
                String s = s2.next();
                System.out.println(s);
            }
        }
    }
}
