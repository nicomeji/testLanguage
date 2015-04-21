package parser.operators;

import org.junit.Test;

public class OperatorTest {
    private Operator operator;

    /**
     * Every operator must have a priority. The lowest priority possible is for
     * the equals, so by default it gets the next lowest priority.
     */
    @Test
    public void operatorHasPriority() {
        operator = new Operator("=");
    }
}
