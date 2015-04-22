package parser.operators;

import java.util.List;

import javax.management.BadAttributeValueExpException;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class OperatorTest {
    private static final String NAME = "Operator";
    private static final String SYMBOL = "=";
    private static final String DESCRIPTION = "Some description";
    private static Operator operator;

    @BeforeClass
    public static void generalSetUp() throws BadAttributeValueExpException {
        operator = new Operator(NAME, SYMBOL, DESCRIPTION, OperatorsPriorities.MIN_VALUE);
    }

    @Test
    public void operatorsHasName() {
        assertThat(operator.getName(), is(equalTo(NAME)));
    }

    @Test
    public void operatorsHasDescription() {
        assertThat(operator.getDescription(), is(equalTo(DESCRIPTION)));
    }

    @Test
    public void operatorsHasPriority() {
        assertThat(operator.getPriority(), is(equalTo(OperatorsPriorities.MIN_VALUE)));
    }

    @Test
    public void operatorsHasMatcher_1() {
        String testSentence = "number=1;";
        List<Integer> indexes = operator.indexesOfOperator(testSentence);
        assertThat(indexes, contains(6));
    }

    @Test
    public void operatorsHasMatcher_2() {
        String testSentence = "number1=number2;";
        List<Integer> indexes = operator.indexesOfOperator(testSentence);
        assertThat(indexes, contains(7));
    }

    @Test
    public void operatorsHasMatcher_3() {
        String testSentence = "number1=number2=0;";
        List<Integer> indexes = operator.indexesOfOperator(testSentence);
        assertThat(indexes, contains(7,15));
    }
}
