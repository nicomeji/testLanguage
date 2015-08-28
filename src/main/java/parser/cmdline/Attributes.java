package parser.cmdline;

import java.io.File;
import java.util.List;

import lombok.Getter;

import org.apache.commons.cli.Option;

public class Attributes {
    @Getter
    private final List<File> sourceFiles;

    @Getter
    private final boolean debugMode;

    public Attributes(Option[] parsedOptions) {
        this.sourceFiles = OptionsAvailable.SOURCE_FILE.getValueFrom(parsedOptions);
        this.debugMode = OptionsAvailable.DEBUG.getValueFrom(parsedOptions);
    }
}
