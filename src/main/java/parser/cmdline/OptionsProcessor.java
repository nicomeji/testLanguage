package parser.cmdline;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
        Map<OptionsAvailable, List<String>> optionsMap = new EnumMap<OptionsAvailable,  List<String>>(OptionsAvailable.class);
        CommandLine cmd = new PosixParser().parse(options, args);
        for (Option opt : cmd.getOptions()) {
            if (! opt.getValuesList().isEmpty()) {
                optionsMap.put(OptionsAvailable.valueOf(opt), Arrays.asList(opt.getValues()));
            }
        }
        return new Attributes(optionsMap);
    }
}
