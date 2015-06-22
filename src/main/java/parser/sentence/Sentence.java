package parser.sentence;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

public class Sentence {
    @Getter
    private final String type;

    @Getter
    private final String returnedType;

    @Getter
    private final List<Sentence> nestedSentence;

    public boolean isToken () {
    	return nestedSentence.isEmpty();
    }

    public Sentence (String type, String returnedType, List<Sentence> nestedSentence) {
        this.type=type;
        this.returnedType = returnedType;
        this.nestedSentence = Collections.unmodifiableList(nestedSentence);
    }
}
