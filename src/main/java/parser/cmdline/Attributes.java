package parser.cmdline;

import java.io.File;
import java.util.List;

import lombok.Data;

import org.apache.commons.cli.Option;

@Data
public class Attributes {
    private final List<File> sourceFiles;
    private final boolean debugMode;

    public Attributes(Option[] parsedOptions) {
        this.sourceFiles = OptionsAvailable.SOURCE_FILE.getValueFrom(parsedOptions);
        this.debugMode = OptionsAvailable.DEBUG.getValueFrom(parsedOptions);
    }
}
