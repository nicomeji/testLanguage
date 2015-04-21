package parser.operators;

import lombok.Getter;

public class Operator {
    @Getter
    private String name;

    @Getter
    private String operator;

    public Operator(String operator) {
        this.operator = operator;
    }
}
