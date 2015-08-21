package parser.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public class IntStreamBuilder {
    private Stream<Character> charStream = Stream.empty();

    private IntStreamBuilder(Stream<String> in) {
        in.map(String::toCharArray).map(charArray -> {
            return Arrays.stream(charArray);
        }).forEach(string -> {
            charStream = Stream.concat(charStream, string.map(this::intToChar));
        });
    }

    private Character intToChar (int a) {
        return (char) a;
    }

    public static IntStream of(Stream<String> in) {
        return new IntStreamBuilder(in).getCharStream().map(value-> new Character(Character.toChars(value)[0]));
    }
}
