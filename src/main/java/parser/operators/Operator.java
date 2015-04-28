package parser.operators;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import parser.SimpleWord;
import lombok.Getter;

public class Operator {
    private Pattern pattern;
    @Getter
    private String name;
    @Getter
    private String symbol;
    @Getter
    private String description;
    @Getter
    private Priority priority;

    Operator(String name, String symbol, String description, Priority priority) {
        if (name == null || symbol == null || description == null || priority == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.priority = priority;
    }

    public List<Integer> indexesOfOperator(String line) {
        List<Integer> indexes = Collections.emptyList();
        if (matcher.find()) {
            indexes = IntStream.range(0, matcher.groupCount()).boxed().map(index -> {
                return matcher.group(index).indexOf(symbol);
            }).collect(Collectors.toList());
            if (indexes.isEmpty()) {
                indexes = Arrays.asList(line.indexOf(symbol));
            }
        }
        return indexes;
    }
}
