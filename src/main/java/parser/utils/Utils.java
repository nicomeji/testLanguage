package parser.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static <T> List<T> nullGuard(T[] items) {
        if (items == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(items);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] getArrayFrom(T... array) {
        return array;
    }
}
