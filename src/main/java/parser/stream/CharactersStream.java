package parser.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

public class CharactersStream implements Supplier<Optional<String>> {
    private final FileInputStream source;

    public CharactersStream(FileInputStream in) {
        this.source = in;
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
