package parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import parser.context.Context;

public class Parser {
    public Context parse(File f) {
        try (Stream<String> lines = Files.lines(f.toPath())) {
            Stream<Character> iterator=lines.flatMap(string -> {
                return string.chars().mapToObj(character -> (char) character).map(Character::new);
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Problem with file: " + f, e.getCause());
        }
        return null;
    }
}
