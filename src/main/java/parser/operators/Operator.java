package parser.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class Operator {
    public static final int LONG_STRING = 100;

    private static class SubString {
        @Getter
        private final int start;
        @Getter
        private final String string;

        private SubString(int start, String string) {
            this.start = start;
            this.string = string;
        }

        public static List<SubString> split(String longString) {
            List<SubString> subStrings = new ArrayList<Operator.SubString>();
            if (longString.length() > 100) {
                int length = longString.length() / 2;
                subStrings.add(new SubString(0, longString.substring(0, length)));
                subStrings.add(new SubString(length, longString.substring(length)));
            } else {
                subStrings.add(new SubString(0, longString));
            }
            return subStrings;
        }
    }

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

    private List<Integer> indexesForLongString(String line) {
        List<Integer> indexes = Collections.synchronizedList(new ArrayList<Integer>());
        SubString.split(line).parallelStream().forEach(subString -> {
            indexes.addAll(indexes(subString.getString()).stream().map(index -> {
                return index + subString.getStart();
            }).collect(Collectors.toList()));
        });
        return indexes;
    }

    private List<Integer> indexesForShortString(String line) {
        List<Integer> indexes = new ArrayList<Integer>();
        int actualIndex = line.indexOf(symbol), lastIndex = 0;
        while (actualIndex >= 0) {
            indexes.add(actualIndex + lastIndex);
            lastIndex += (actualIndex + 1);
            actualIndex = (line = line.substring(actualIndex + 1)).indexOf(symbol);
        }
        return indexes;
    }

    public List<Integer> indexes(String line) {
        List<Integer> returnedList;
        if (line.length() > LONG_STRING) {
            returnedList = indexesForLongString(line);
        } else {
            returnedList = indexesForShortString(line);
        }
        return returnedList.stream().sorted().collect(Collectors.toList());
    }
}
