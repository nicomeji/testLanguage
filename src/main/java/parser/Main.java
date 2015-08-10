package parser;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class Main implements Runnable{
    private final List<File> sourceFiles;
    private final Parser parser;

    public static void main(String[] args) throws ParseException {
        Main program = new Main(args);
        program.run();
    }

    public Main (String[] args) throws ParseException {
        Option option = new Option("f", "file", true,
                "Source file to be parsed.");
        Options options = new Options();
        options.addOption(option);
        CommandLine cmd = new PosixParser().parse(options, args);

        sourceFiles = getSources(cmd).stream().filter(this::filterReadableFiles).collect(Collectors.toList());
        parser = new Parser();
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

    private boolean filterReadableFiles (File f) {
        if (f != null && f.canRead()) {
            return true;
        }
        System.err.println("File " + f.getPath() + " cannot be read.");
        return false;
    }

    @Override
    public void run() {
        sourceFiles.stream().parallel().map(parser::parse).collect(Collectors.toList());
    }
}
