package parser.stream;

import java.util.ArrayList;
import java.util.List;

abstract public class Key<T> {
    public static final Key<Character> END_SENTESE;
    public static final Key<Character> OPEN_BLOCK;
    public static final Key<Character> END_BLOCK;
    public static final Key<Character> OPEN_TUPLE;
    public static final Key<Character> END_TUPLE;

    static {
        END_SENTESE = new Key<Character>(';', ParseLevel.CHARACTER) {
            @Override
            public boolean matches(Character a) {
                return symbol.equals(a);
            }
        };

        OPEN_BLOCK = new Key<Character>('{', ParseLevel.CHARACTER) {
            @Override
            public boolean matches(Character a) {
                return symbol.equals(a);
            }
        };

        END_BLOCK = new Key<Character>('}', ParseLevel.CHARACTER) {
            @Override
            public boolean matches(Character a) {
                return symbol.equals(a);
            }
        };

        OPEN_TUPLE = new Key<Character>('(', ParseLevel.CHARACTER) {
            @Override
            public boolean matches(Character a) {
                return symbol.equals(a);
            }
        };

        END_TUPLE = new Key<Character>(')', ParseLevel.CHARACTER) {
            @Override
            public boolean matches(Character a) {
                return symbol.equals(a);
            }
        };
    }

    public static final List<Key<? extends Object>> characterKeysAvailable = new ArrayList<Key<? extends Object>>();
    public static final List<Key<? extends Object>> wordKeysAvailable = new ArrayList<Key<? extends Object>>();
    protected final T symbol;

    public Key(T symbol, ParseLevel level) {
        this.symbol = symbol;
        switch (level) {
        case CHARACTER:
            Key.characterKeysAvailable.add(this);
            break;
        case WORD:
            Key.wordKeysAvailable.add(this);
            break;
        }
    }

    public abstract boolean matches(T a);

    public static Key<Character> valueOf(Character a) {
        for (Key<? extends Object> key : characterKeysAvailable) {
            if (key.symbol.equals(a)) {
                return (Key<Character>) key;
            }
        }
        return null;
    }

    public static Key<String> valueOf(String a) {
        for (Key<? extends Object> key : wordKeysAvailable) {
            if (key.symbol.equals(a)) {
                return (Key<String>) key;
            }
        }
        return null;
    }
}
