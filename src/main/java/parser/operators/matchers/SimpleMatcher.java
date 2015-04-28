package parser.operators.matchers;

import static java.util.Arrays.asList;

public class SimpleMatcher {
    private final String regex;

    /**
     * @param firstRegex At least must have one regex.
     * @param regexs
     */
    public SimpleMatcher(String firstRegex, String ... regexs) {
        StringBuilder sb = new StringBuilder(firstRegex.trim());
        asList(regexs).forEach(regex -> {
            sb.append('|').append(regex.trim());
        });
        this.regex = sb.toString();
    }

    @Override
    public String toString() {
        return regex;
    }
}
