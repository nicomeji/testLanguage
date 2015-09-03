package parser.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import lombok.Data;

@Data
public class Operator {
    private final String name;
    private final String symbol;
    private final String description;
    private final Pattern pattern;

    Operator(String name, String symbol, String description) {
        if (name == null || symbol == null || description == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        try {
            this.pattern = Pattern.compile(Pattern.quote(symbol));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(e.getCause());
        }
    }

    @Deprecated
    public List<Integer> indexes(String line) {
        Matcher matcher = pattern.matcher(line);
        List<Integer> indexes = new ArrayList<Integer>();
        while (matcher.find()) {
            indexes.add(matcher.start());
        }
        return indexes;
    }
}
