package parser.operators;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OperatorPriorityTest {
    @Test
    public void priorityHasValue() {
        assertThat(OperatorsPrioritiesLALA.MAX_VALUE.getValue(), is(1));
    }

    @Test
    public void prioritiesAreComparable() {
        OperatorsPrioritiesLALA p1 = new OperatorsPrioritiesLALA(OperatorsPrioritiesLALA.MAX_VALUE);
        OperatorsPrioritiesLALA p0 = new OperatorsPrioritiesLALA(p1);
        assertThat(p1, is(greaterThan(p0)));
    }
}
