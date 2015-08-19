package parser.cmdline;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class OptionsProcessor {
    private Options options = new Options();

    public OptionsProcessor(Properties prop) {
        OptionsAvailable.listOptions().stream().forEach(opt -> {
            Option option = new Option(opt.shortName, opt.longName, opt.hasArg, prop.getProperty(opt.descriptionKey));
            option.setRequired(opt.required);
            option.setArgs(opt.hasArg ? opt.qArg : 0);
            option.setArgName(opt.toString());
            options.addOption(option);
        });
    }

    public Attributes process(String[] args) throws ParseException {
        CommandLine cmd = new PosixParser().parse(options, args);
        return new Attributes(Arrays.asList(cmd.getOptions()).stream().collect(
                        Collectors.groupingBy(OptionsAvailable::valueOf, () -> new EnumMap<>(OptionsAvailable.class),
                                Collectors.reducing((opt1, opt2) -> {
                                    System.out.println(opt1);
                                    System.out.println(opt2);
                                    return opt1;
                                }))));
    }
}
