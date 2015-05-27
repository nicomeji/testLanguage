package parser.sentence;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

public class Sentence {
    @Getter
    protected final String type;

    @Getter
    protected final String returnedType;

    @Getter
    protected final List<Token> tokens;

    protected Sentence (String type, String returnedType, List<Token> tokens) {
        this.type=type;
        this.returnedType = returnedType;
        this.tokens = Collections.unmodifiableList(tokens);
    }

    public Sentence(String sentence) throws IllegalArgumentException {
        validateInput(sentence);
        // TODO
        this.type = null;
        this.returnedType=null;
        this.tokens=null;
    }

    private void validateInput(String sentence)
            throws IllegalArgumentException {
        if (sentence.indexOf(';') + 1 != sentence.length()) {
            throw new IllegalArgumentException(
                    "Every Sentence must end with semicolon.");
        }
    }
}
