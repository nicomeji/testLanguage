package parser.cmdline;

import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class ArgumentsProcessor {
    private Options options;

    public ArgumentsProcessor(List<Option> options){
        options.forEach(option -> this.options.addOption(option));
    }

    public void parseArgs (String[] args) {
        
    }
}
