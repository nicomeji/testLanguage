package parser.stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.stream.Stream;

import org.junit.Test;

public class IntStreamBuilderTest {
    @Test
    public void testParsingString () {
        Stream<String> strings = Stream.of("Hello world");
        Stream<Character> stream = IntStreamBuilder.of(strings);
        assertThat(stream, is(not(nullValue())));
        assertThat(stream.count(), is(11L));
    }

    @Test
    public void testCharactersOrder () {
        Stream<String> strings = Stream.of("12", "3", "45");
        Stream<Character> stream = IntStreamBuilder.of(strings);
        assertThat(stream, is(not(nullValue())));
        Iterator<Character> iterator = stream.iterator();
        assertThat(iterator.next(), is('1'));
        assertThat(iterator.next(), is('2'));
        assertThat(iterator.next(), is('3'));
        assertThat(iterator.next(), is('4'));
    }
}
