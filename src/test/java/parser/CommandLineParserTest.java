package parser;

import org.apache.commons.cli.ParseException;

import parser.cmdline.Attributes;

public class CommandLineParserTest {
    Parser parserMock;
    Attributes attributesMock;

    public void simpleExecution() throws ParseException {
        Main main = new Main(attributesMock, parserMock);
    }

    private String[] getArgs(String... a) {
        return a;
    }
}
