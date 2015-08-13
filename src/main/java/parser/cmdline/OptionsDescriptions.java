package parser.cmdline;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

public class OptionsDescriptions extends ResourceBundle {
    private final Properties descriptions;

    public OptionsDescriptions(Properties prop) {
        this.descriptions = prop;
    }

    @Override
    protected Object handleGetObject(String key) {
        return descriptions.getProperty(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return new Vector<String>(descriptions.stringPropertyNames()).elements();
    }
}
