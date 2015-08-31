package parser.stream;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import parser.operators.ReservedKeys;

public class WordsStream implements Supplier<Optional<String>> {
    private final CharactersStream source;
    private final Pattern pattern;

    public WordsStream(CharactersStream in) {
        this.pattern = Pattern.compile("[" + ReservedKeys.BLANK_PATTERN + ReservedKeys.END_BLOCK + ReservedKeys.STAR_BLOCK + ReservedKeys.END_SENTENCE + "]");
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
        Optional<String> character = source.get();
        StringBuilder word = new StringBuilder();
        while (character.isPresent() && !pattern.matcher(character.get()).find()) {
            word.append(character.get());
        }
        return word.length() == 0 ? null : word.toString();
    }
}
