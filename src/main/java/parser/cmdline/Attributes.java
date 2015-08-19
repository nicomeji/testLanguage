package parser.cmdline;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;

import org.apache.commons.cli.Option;

public class Attributes {
    @Getter
    private List<File> sourceFiles = new ArrayList<File>();

    public Attributes(EnumMap<OptionsAvailable, Optional<Option>> options) {
        // Nadie garantiza q options.get(OptionsAvailable.SOURCE_FILE) no es nulo
        options.get(OptionsAvailable.SOURCE_FILE).ifPresent(this::setSourceFiles);
        System.out.println("lalal");
    }

    private void setSourceFiles(Option opt) {
        List<String> values = opt.getValues() != null ?Arrays.asList(opt.getValues()) : OptionsAvailable.SOURCE_FILE.getDefaultValue();
        this.sourceFiles = values.stream().map(File::new).collect(Collectors.toList());
    }
}
