package parser.sentence;

import lombok.Getter;

public class SimpleSentence {
    @Getter
    private final String returnedType;

    public SimpleSentence (String returnedType) {
        this.returnedType = returnedType;
    }
}
