package parser.sentence;

public class SentenceFactory {
    private final char separator;

    public SentenceFactory(char separator) {
        this.separator = separator;
    }

    public Sentence processSentence(String sentence)
            throws IllegalArgumentException {
        validateInput(sentence);
        return new Sentence(null, null, null);
    }

    private void validateInput(String sentence) throws IllegalArgumentException {
        if (sentence.indexOf(separator) + 1 != sentence.length()) {
            throw new IllegalArgumentException(
                    "Every Sentence must end with \"" + separator + "\".");
        }
    }
}