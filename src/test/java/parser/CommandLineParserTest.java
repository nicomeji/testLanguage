package parser;

import org.apache.commons.cli.ParseException;
import org.springframework.context.ApplicationContext;

public class CommandLineParserTest {
    ApplicationContext applicationContextMock;

    public void simpleExecution() throws ParseException {
        Main main = new Main(getArgs("a"), applicationContextMock);
    }

    private String[] getArgs(String... a) {
        return a;
    }
}
