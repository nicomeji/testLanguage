package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

public class SourceSupplier implements Supplier<Optional<String>> {
    private final FileInputStream source;

    private SourceSupplier(FileInputStream in) {
        this.source = in;
    }

    public static SourceSupplier of(File source) throws FileNotFoundException {
        return new SourceSupplier(new FileInputStream(source));
    }

    @Override
    public Optional<String> get() {
        String nextValue = null;
        try {
            nextValue = getNextElement();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(nextValue);
    }

    private String getNextElement() throws IOException {
        int character = source.read();
        if (character >= 0) {
            return new Character((char) character).toString();
        }
        return null;
    }
}
