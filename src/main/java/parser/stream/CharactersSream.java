package parser.stream;

import java.util.stream.Stream;

public class CharactersSream {
    private final Stream<String> source;

    public CharactersSream(Stream<String> in) {
        this.source = in;
    }

    public static Stream<Character> of (Stream<String> in) {
        return null;
    }
}
