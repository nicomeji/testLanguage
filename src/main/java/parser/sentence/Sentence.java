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
    protected final List<Sentence> nestedSentence;

    public boolean isToken () {
    	return nestedSentence.isEmpty();
    }

    public Sentence (String type, String returnedType, List<Sentence> nestedSentence) {
        this.type=type;
        this.returnedType = returnedType;
        this.nestedSentence = Collections.unmodifiableList(nestedSentence);
    }
}
