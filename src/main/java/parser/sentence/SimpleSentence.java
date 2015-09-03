package parser.sentence;

import lombok.Data;

@Data
public class SimpleSentence {
    private final String returnedType;

    public SimpleSentence (String returnedType) {
        this.returnedType = returnedType;
    }
}
