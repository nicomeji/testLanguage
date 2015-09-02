package parser.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CharactersSupplier implements Supplier<String> {
    private final FileInputStream source;

    public CharactersSupplier(FileInputStream in) {
        this.source = in;
    }

    public Stream<String> stream() {
        return Stream.generate(this).limit(getFileSize());
    }

    @Override
    public String get() {
        String nextValue = null;
        try {
            nextValue = getNextElement();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nextValue;
    }

    private String getNextElement() throws IOException {
        int character = source.read();
        if (character >= 0) {
            return new Character((char) character).toString();
        }
        return null;
    }

    private long getFileSize() {
        try {
            return source.getChannel().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
