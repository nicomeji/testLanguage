package parser.cmdline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

import org.apache.commons.cli.Option;

public class Attributes {
    @Getter
    private List<FileInputStream> sourceFiles= new ArrayList<FileInputStream>();;

    public Attributes(List<Option> options) {
        for (Option option : options) {
            if (OptionsAvailable.SOURCE_FILE.getShortName().equals(option.getArgName())){
                List<String> values = option.getValues() != null ?Arrays.asList(option.getValues()) : OptionsAvailable.SOURCE_FILE.getDefaultValue();
                sourceFiles = values.stream().map(this::checkFiles).collect(Collectors.toList());
            }
        }
    }

    private FileInputStream checkFiles(String f) {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(new File(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stream;
    }
}
