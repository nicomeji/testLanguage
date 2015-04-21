package parser.operators;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PriorityTest {
    @Test
    public void priorityHasValue() {
        assertThat(Priority.createLowest().getValue(), is(1));
    }

    @Test
    public void prioritiesAreComparable() {
        Priority p1 = Priority.createLowest();
        Priority p2 = Priority.createLowerThan(p1);
        assertThat(p1.compareTo(p2), is(greaterThan(0)));
    }
}
