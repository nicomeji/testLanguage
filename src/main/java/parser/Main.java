package parser;

import java.io.FileNotFoundException;
import java.util.stream.Collectors;

import org.apache.commons.cli.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import parser.cmdline.Arguments;
import parser.cmdline.ArgumentsProcessor;

public class Main implements Runnable {
    private final Arguments arguments;
    private final Parser parser;

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"CommandLineParserContext.xml"});
        Main program = new Main(args, context);
        program.run();
    }

    public Main(String[] args, ApplicationContext applicationContext) throws ParseException, FileNotFoundException {
        ArgumentsProcessor cmdParser = (ArgumentsProcessor) applicationContext.getBean("cmdlineProcessor");
        this.arguments = cmdParser.parseArgs(args);
        this.parser = new Parser();
    }

    @Override
    public void run() {
        arguments.getSources().stream().parallel().map(parser::parse).collect(Collectors.toList());
    }
}
