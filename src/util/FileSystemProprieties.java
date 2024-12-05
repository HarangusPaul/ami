package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileSystemProprieties {
    private FileReader reader;
    private Properties properties;

    public FileSystemProprieties() throws IOException {
       reader = new FileReader("src/settings.properties");
       properties = new Properties();
       properties.load(reader);
    }

    public String readProprieties(String var) {
        return properties.getProperty(var);
    }
}
