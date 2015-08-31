package parser.operators;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

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

    @Test
    public void operatorsHasMatcher() {
        String testSentence = "number=1;";
        List<Integer> indexes = operator.indexes(testSentence);
        assertThat(indexes, contains(6));
    }

    @Test
    public void operatorsHasMatcher_1() {
        String testSentence = "number1=number2;";
        List<Integer> indexes = operator.indexes(testSentence);
        assertThat(indexes, contains(7));
    }

    @Test
    public void operatorsHasMatcher_2() {
        String testSentence = "number1=number2 =   0;";
        List<Integer> indexes = operator.indexes(testSentence);
        assertThat(indexes, contains(7, 16));
    }

    @Test
    public void operatorsProcessLongString() {
        String testSentence = "number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number=number";
        List<Integer> indexes = operator.indexes(testSentence);
        assertThat(
                indexes,
                contains(6, 13, 20, 27, 34, 41, 48, 55, 62, 69, 76, 83, 90, 97, 104, 111, 118, 125, 132, 139, 146, 153,
                        160, 167, 174, 181, 188, 195, 202, 209, 216, 223, 230, 237, 244, 251, 258, 265, 272, 279, 286,
                        293, 300, 307, 314, 321, 328, 335, 342, 349, 356, 363, 370, 377, 384, 391, 398, 405, 412, 419,
                        426, 433, 440, 447, 454, 461, 468, 475, 482, 489, 496, 503, 510, 517, 524, 531, 538, 545, 552,
                        559, 566, 573, 580, 587, 594, 601, 608, 615, 622, 629, 636, 643, 650, 657, 664, 671, 678, 685,
                        692, 699, 706, 713, 720, 727, 734, 741, 748, 755, 762));
    }
}
