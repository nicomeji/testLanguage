package parser;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class Parser {
    public static void main(String[] args) throws ParseException {
        Option option = new Option("f", "file", true, "Source file to be parsed.");
        Options options = new Options();
        options.addOption(option);
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);

        List<File> files = getSources(cmd);
        System.out.println(files);
    }

    private static List<File> getSources(CommandLine cmd) throws ParseException {
        List<String> sources = Arrays.asList(cmd.getArgs());
        if (cmd.hasOption("f") && sources.isEmpty()) {
            sources = Arrays.asList(cmd.getOptionValues("f"));
        } else if (sources.isEmpty()) {
            throw new ParseException("");
        }
        return sources.stream().map(File::new).collect(Collectors.toList());
    }
}
