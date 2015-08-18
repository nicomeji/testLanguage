package parser.cmdline;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import org.apache.commons.cli.Option;
import org.apache.commons.io.FilenameUtils;

public enum OptionsAvailable {
    SOURCE_FILE("f", "sources-files", "sourceFilesOptDescription", true, false, Option.UNLIMITED_VALUES) {
        @Override
        public List<String> getDefaultValue() {
            Arrays.asList(new File(".").listFiles()).stream().parallel().filter(file -> {
                return file.isFile() && FilenameUtils.isExtension(file.getName(), "n");
            });
            return Arrays.asList();
        }
    },
    DEBUG("d", "debug", "debugOptDescription", false, false, 0);

    private OptionsAvailable(String shortName, String longName, String descriptionKey, boolean required, boolean hasArg, int qArg) {
        this.descriptionKey = descriptionKey;
        this.shortName = shortName;
        this.longName = longName;
        this.required = required;
        this.hasArg = hasArg;
        this.qArg = qArg;
    }

    @Getter
    public final String shortName;
    public final String descriptionKey;
    public final String longName;
    public final boolean required;
    public final boolean hasArg;
    public final int qArg;

    public List<String> getDefaultValue() {
        return Arrays.asList();
    }

    public static List<OptionsAvailable> listOptions() {
        return Arrays.asList(values());
    }
}
