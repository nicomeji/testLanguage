package parser.operators;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.management.BadAttributeValueExpException;

import org.junit.BeforeClass;
import org.junit.Test;

public class OperatorTest {
    private static final String NAME = "Operator";
    private static final String SYMBOL = "=";
    private static final String DESCRIPTION = "Some description";
    private static Operator operator;

    @BeforeClass
    public static void generalSetUp() throws BadAttributeValueExpException {
        operator = new Operator(NAME, SYMBOL, DESCRIPTION);
    }

    @Test
    public void operatorsHasName() {
        assertThat(operator.getName(), is(equalTo(NAME)));
    }

    @Test
    public void operatorsHasDescription() {
        assertThat(operator.getDescription(), is(equalTo(DESCRIPTION)));
    }
}
