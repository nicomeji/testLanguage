package parser.cmdline;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.Option;
import org.apache.commons.io.FilenameUtils;

import static parser.utils.Utils.asStream;

public abstract class OptionsAvailable<T> {
    public static final OptionsAvailable<List<File>> SOURCE_FILE;
    public static final OptionsAvailable<Boolean> DEBUG;

    private static final List<OptionsAvailable<? extends Object>> optionsAvailable = new ArrayList<OptionsAvailable<? extends Object>>();

    static {
        SOURCE_FILE = new OptionsAvailable<List<File>>("f", "sources-files", "sourceFilesOptDescription", true, true, Option.UNLIMITED_VALUES) {
            @Override
            protected List<File> getDeaultValue() {
                return Arrays.asList(new File(".").listFiles()).stream().parallel().filter(file -> {
                    return file.isFile() && FilenameUtils.isExtension(file.getName(), "n");
                }).collect(Collectors.toList());
            }

            @Override
            protected List<File> getParsedValues(Option opt) {
                return Arrays.asList(opt.getValues()).stream().map(File::new).collect(Collectors.toList());
            }
        };
        DEBUG = new OptionsAvailable<Boolean>("d", "debug", "debugOptDescription", false, false, 0) {
            @Override
            protected Boolean getDeaultValue() {
                return false;
            }

            @Override
            protected Boolean getParsedValues(Option opt) {
                return Boolean.valueOf(opt.getValue());
            }
        };
    }

    protected OptionsAvailable(String shortName, String longName, String descriptionKey, boolean required, boolean hasArg, int qArg) {
        this.descriptionKey = descriptionKey;
        this.shortName = shortName;
        this.longName = longName;
        this.required = required;
        this.hasArg = hasArg;
        this.qArg = qArg;

        OptionsAvailable.optionsAvailable.add(this);
    }

    public final String shortName;
    public final String descriptionKey;
    public final String longName;
    public final boolean required;
    public final boolean hasArg;
    public final int qArg;

    public T getValueFrom(Option[] parsedOptions) {
        Option opt = getOption(parsedOptions);
        return opt != null && opt.getValues() != null ? getParsedValues(opt) : getDeaultValue();
    }

    protected abstract T getDeaultValue();

    protected abstract T getParsedValues(Option opt);

    public static List<OptionsAvailable<? extends Object>> listOptions() {
        return Collections.unmodifiableList(optionsAvailable);
    }

    public static OptionsAvailable<? extends Object> valueOf(Option opt) {
        return listOptions().stream().filter(opt.getLongOpt()::equals).findFirst().orElse(null);
    }

    private Option getOption(Option[] opts) {
        return asStream(opts).filter(opt -> {
            return this.longName.equals(opt.getLongOpt());
        }).findFirst().orElse(null);
    }
}
