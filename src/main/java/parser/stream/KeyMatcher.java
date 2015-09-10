package parser.stream;

public class KeyMatcher {
    public Key<Character> characterMatches (Character a) {
        return Key.valueOf(a);
    }

    public Key<String> characterMatches (String a) {
        return Key.valueOf(a);
    }
}
