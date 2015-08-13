package parser.cmdline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class ArgumentsProcessor {
    private final Options options;

    public ArgumentsProcessor(List<Option> options) {
        if (options == null) {
            throw new IllegalArgumentException("Missing required argument.");
        }
        this.options = new Options();
        options.forEach(option -> this.options.addOption(option));
    }

    public Arguments parseArgs(String[] args) throws ParseException, FileNotFoundException {
        CommandLine cmd = new PosixParser().parse(options, args);
        return new Arguments(getSources(cmd));
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
}
