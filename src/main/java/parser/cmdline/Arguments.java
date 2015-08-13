package parser.cmdline;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import com.beust.jcommander.Parameter;

public class Arguments {
    @Getter
    @Parameter(names = { "-f", "--source-file" }, required = true, descriptionKey = "sourceFilesOptDescription")
    private List<String> sources = new ArrayList<String>();

    @Override
    public String toString() {
        return "sources=" + sources.toString();
    }
}
