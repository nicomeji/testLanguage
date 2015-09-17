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

    public Operator(String name, String symbol, String description) {
        if (name == null || symbol == null || description == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.symbol = symbol;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Operator) {
            return symbol.equals(((Operator) o).symbol);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
