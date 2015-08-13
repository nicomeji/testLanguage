package parser.cmdline;

import java.io.FileInputStream;
import java.util.List;

import lombok.Getter;

public class Arguments {
    @Getter
    private final List<FileInputStream> sources;

    public Arguments(List<FileInputStream> sources) {
        this.sources = sources;
    }
}
