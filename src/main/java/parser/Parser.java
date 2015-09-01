package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import parser.stream.CharactersSupplier;

public class Parser {
    public Object parse(File f) {
        try (FileInputStream stream = new FileInputStream(f)) {
            new CharactersSupplier(stream).stream();
        } catch (IOException e) {
            throw new IllegalArgumentException("Problem with file: " + f, e.getCause());
        }
        return null;
    }
}
