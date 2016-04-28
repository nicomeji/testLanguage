package parser;

import java.util.stream.Collectors;

import org.apache.commons.cli.ParseException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import parser.cmdline.Attributes;
import parser.cmdline.OptionsProcessor;

public class Main implements Runnable {
    private final Attributes attributes;
    private final Parser parser;

    public static void main(String[] args) throws ParseException {
        try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("CommandLineParserContext.xml")) {
            OptionsProcessor optionsProcessor = (OptionsProcessor) context.getBean("cmdLineProcessor");
            Parser parser = (Parser) context.getBean("parser");
            Main program = new Main(optionsProcessor.process(args), parser);
            program.run();
        }
    }

    public Main(Attributes attributes, Parser parser) {
        this.attributes = attributes;
        this.parser = parser;
    }

    @Override
    public void run() {
        attributes.getSourceFiles().stream().parallel().map(parser::parse).collect(Collectors.toList());
    }
}
