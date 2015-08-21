package parser.stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class IntStreamBuilderTest {
    @Test
    public void testParsingString () {
        Stream<String> strings = Stream.of("Hello world");
        IntStream stream = IntStreamBuilder.of(strings);
        assertThat(stream, is(not(nullValue())));
        assertThat(stream.count(), is(11L));
    }

    @Test
    public void testCharactersOrder () {
        Stream<String> strings = Stream.of("12", "45");
        IntStream stream = IntStreamBuilder.of(strings);
        assertThat(stream, is(not(nullValue())));
        OfInt iterator = stream.iterator();
        assertThat(iterator.next(), is('1'));
        assertThat(iterator.next(), is('2'));
        assertThat(iterator.next(), is('3'));
        assertThat(iterator.next(), is('4'));
    }
}
