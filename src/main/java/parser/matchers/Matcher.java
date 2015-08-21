package parser.matchers;

public interface Matcher<KEY, ELEMENT> {
    public enum Status {
        MATCHED, NOT_MATCHED, PENDING
    };

    public abstract KEY getKey ();

    public abstract Status getStatus ();

    public abstract void parse(ELEMENT a);

    public abstract void reset();
}
