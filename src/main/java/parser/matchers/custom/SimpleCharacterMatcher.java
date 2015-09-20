package parser.matchers.custom;

import parser.matchers.CharacterMatcher;

public class SimpleCharacterMatcher extends CharacterMatcher {
    private int index;

    public SimpleCharacterMatcher(String symbol) {
        super(symbol);
    }

    @Override
    public void clearChanged() {
        super.clearChanged();
        index = 0;
    }

    @Override
    public void parse(Character a) {
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
