package parser.cmdline;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

public class Attributes {
    @Getter
    private final List<File> sourceFiles;

    public Attributes(Map<OptionsAvailable, List<String>> optionsMap) {
        List<String> values = optionsMap.getOrDefault(OptionsAvailable.SOURCE_FILE, OptionsAvailable.SOURCE_FILE.getDefaultValue());
        this.sourceFiles = values.stream().map(File::new).collect(Collectors.toList());
    }
}
