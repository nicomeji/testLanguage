package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main implements Runnable {
    private final List<FileInputStream> sourceFiles;
    private final Parser parser;

    public static void main(String[] args) throws ParseException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Main program = new Main(args, context);
        program.run();
    }

    public Main(String[] args, ApplicationContext applicationContext) throws ParseException {
        Option option = new Option("f", "file", true, "Source file to be parsed.");
        Options options = new Options();
        options.addOption(option);
        CommandLine cmd = new PosixParser().parse(options, args);

        sourceFiles = getSources(cmd).stream().map(this::checkFiles).collect(Collectors.toList());
        this.parser = null;
    }

    private List<File> getSources(CommandLine cmd) throws ParseException {
        List<String> sources = Arrays.asList(cmd.getArgs());
        if (cmd.hasOption("f") && sources.isEmpty()) {
            sources = Arrays.asList(cmd.getOptionValues("f"));
        } else if (sources.isEmpty()) {
            throw new ParseException("");
        }
        return sources.stream().map(File::new).collect(Collectors.toList());
    }

    private FileInputStream checkFiles(File f) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stream;
    }

    @Override
    public void run() {
        sourceFiles.stream().parallel().map(parser::parse).collect(Collectors.toList());
    }
}
