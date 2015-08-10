package parser.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

public class Operator {
    @Getter
    private final String name;

    @Getter
    private final String symbol;

    @Getter
    private final String description;

    @Getter
    private final Priority priority;

    private final Pattern pattern;

    Operator(String name, String symbol, String description, Priority priority) {
        if (name == null || symbol == null || description == null || priority == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.priority = priority;
        this.pattern = Pattern.compile(Pattern.quote(symbol));
    }

    public List<Integer> indexes(String line) {
        Matcher matcher = pattern.matcher(line);
        List<Integer> indexes = new ArrayList<Integer>();
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        return indexes;
    }
}
