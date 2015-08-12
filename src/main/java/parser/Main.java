package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import parser.cmdline.ArgumentsProcessor;

public class Main implements Runnable {
    private final List<FileInputStream> sourceFiles;
    private final Parser parser;
    private final ArgumentsProcessor cmdParser;

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"CommandLineParserContext.xml"});
        Main program = new Main(args, context);
        program.run();
    }

    public Main(String[] args, ApplicationContext applicationContext) throws ParseException, FileNotFoundException {
        Option option = new Option("f", "file", true, "Source file to be parsed.");
        Options options = new Options();
        options.addOption(option);
        CommandLine cmd = new PosixParser().parse(options, args);

        this.sourceFiles = getSources(cmd);
        this.cmdParser = (ArgumentsProcessor) applicationContext.getBean("cmdlineProcessor");
        this.parser = null;
    }

    private List<FileInputStream> getSources(CommandLine cmd) throws ParseException, FileNotFoundException {
        List<String> sources = Arrays.asList(cmd.getArgs());
        if (cmd.hasOption("f") && sources.isEmpty()) {
            sources = Arrays.asList(cmd.getOptionValues("f"));
        } else if (sources.isEmpty()) {
            throw new ParseException("");
        }
        if (sources.isEmpty()) {
            throw new MissingArgumentException("");
        }
        List<FileInputStream> sourceFiles = new ArrayList<FileInputStream>(sources.size());
        for (String src : sources) {
            sourceFiles.add(new FileInputStream(new File(src)));
        }
        return sourceFiles;
    }

    @Override
    public void run() {
        sourceFiles.stream().parallel().map(parser::parse).collect(Collectors.toList());
    }
}
