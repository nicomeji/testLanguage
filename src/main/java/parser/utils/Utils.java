package parser.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Utils {
    public static <T> Stream<T> asStream(T[] items) {
        return nullGuard(items).stream();
    }

    private static <T> List<T> nullGuard(T[] items) {
        if (items == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(items);
        }
    }

    @SafeVarargs
    public static <T> T[] getArrayFrom(T... array) {
        return array;
    }
}
