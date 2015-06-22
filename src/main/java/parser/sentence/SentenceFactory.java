package parser.sentence;

public class SentenceFactory {
	private static final char SEPARATOR = ';';

	public Sentence processSentence(String sentence)
			throws IllegalArgumentException {
		validateInput(sentence);
		return new Sentence(null, null, null);
	}

	private void validateInput(String sentence) throws IllegalArgumentException {
		if (sentence.indexOf(SEPARATOR) + 1 != sentence.length()) {
			throw new IllegalArgumentException(
					"Every Sentence must end with \"" + SEPARATOR + "\".");
		}
	}
}
