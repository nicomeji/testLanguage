package parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

import parser.context.Context;
import parser.stream.IntStreamBuilder;

public class Parser {
    public Context parse(File f) {
        try (Stream<String> lines = Files.lines(f.toPath())) {
            Stream<Character> iterator = IntStreamBuilder.of(lines);
        } catch (IOException e) {
            throw new IllegalArgumentException("Problem with file: " + f, e.getCause());
        }
        return null;
    }
}
