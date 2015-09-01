package parser.stream;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.regex.Pattern;

import parser.operators.ReservedKeys;

public class WordsStream extends CharactersStream {
    private final Pattern pattern;

    public WordsStream(FileInputStream fs) {
        super(fs);
        this.pattern = Pattern.compile("[" + ReservedKeys.BLANK_PATTERN + ReservedKeys.END_BLOCK + ReservedKeys.STAR_BLOCK + ReservedKeys.END_SENTENCE + "]");
    }

    @Override
    public Optional<String> get() {
        return Optional.ofNullable(getNextElement());
    }

    private String getNextElement() {
        Optional<String> character = super.get();
        StringBuilder word = new StringBuilder();
        while (character.isPresent() && !pattern.matcher(character.get()).find()) {
            word.append(character.get());
        }
        return word.length() == 0 ? null : word.toString();
    }
}
