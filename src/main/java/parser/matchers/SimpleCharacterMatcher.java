package parser.matchers;

public class SimpleCharacterMatcher extends CharacterMatcher {
    private int index;

    public SimpleCharacterMatcher(String symbol) {
        super(symbol);
    }

    @Override
    public void reset() {
        super.reset();
        index = 0;
    }

    @Override
    protected void check(Character a) {
        if (symbol.get(index).equals(a)) {
            if (index == symbol.size() - 1) {
                matched();;
            }
            index++;
        } else {
            notMatched();;
        }
    }
}
