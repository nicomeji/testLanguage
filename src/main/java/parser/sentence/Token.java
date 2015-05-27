package parser.sentence;

import java.util.Collections;

public class Token extends Sentence {
    public Token(String type, String returnedType) {
        super(type, returnedType, Collections.emptyList());
    }
}
