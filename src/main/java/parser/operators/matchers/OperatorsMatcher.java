package parser.operators.matchers;

import static java.util.Arrays.asList;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

public class OperatorsMatcher {
    private static final String WHITESPACE = "\\s*";
    private static final String OPEN_GROUP = "(";
    private static final String CLOSE_GROUP = ")";
    private final Pattern regex;
    @Getter
    private final List<SimpleMatcher> operands;
    @Getter
    private final String symbol;

    /**
     * @param symbol
     * @param firstOperand
     *            Every operation has at least one operand.
     * @param operands
     */
    public OperatorsMatcher(String symbol, int position, SimpleMatcher firstOperand, SimpleMatcher... operands) {
        this.symbol = symbol;
        List<SimpleMatcher> listOfOperands = new ArrayList<SimpleMatcher>();
        listOfOperands.add(firstOperand);
        listOfOperands.addAll(asList(operands));
        this.operands = listOfOperands;
        this.regex = buildRegex(position);
    }

    private Pattern buildRegex(int position) {
        StringBuilder sb = new StringBuilder();
        range(0, this.operands.size()).forEach(index -> {
            if (index == position) {
                sb.append(Pattern.quote(this.symbol)).append(WHITESPACE);
            }
            sb.append(OPEN_GROUP).append(this.operands.get(index)).append(CLOSE_GROUP).append(WHITESPACE);
        });
        return Pattern.compile(sb.toString());
    }

    public class Matches {
        @Getter
        private final List<String> operands;
        @Getter
        private final List<String> indexes;

        public boolean hasCoincidences() {
            return operands.size() > 0;
        }

        Matches(List<String> operands, List<String> indexes) {
            this.operands = Collections.unmodifiableList(operands);
            this.indexes = Collections.unmodifiableList(indexes);
        }
    }

    Matches getMatches(String line) {
        Matcher matcher = regex.matcher(line);
        List<String> matches = new ArrayList<String>();
        if (matcher.find() && matcher.groupCount() == operands.size()) {
            range(1, matcher.groupCount() + 1).forEach(index -> {
                matches.add(matcher.group(index));
            });
        }
        return new Matches(matches);
    }
}
