package parser.stream;

import lombok.Data;

@Data
public class Key {
    private final String symbol;

    public Key(String symbol) {
        this.symbol = symbol;
    }
}
