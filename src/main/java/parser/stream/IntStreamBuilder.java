package parser.stream;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public class IntStreamBuilder {
	public static Stream<Character> of(Stream<String> in) {
		return in.flatMap(string -> {
			return string.chars().mapToObj(character -> (char) character).map(Character::new);
		});
	}
}
