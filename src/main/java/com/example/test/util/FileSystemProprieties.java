package com.example.test.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileSystemProprieties {
    private FileReader reader;
    private Properties properties;

    public FileSystemProprieties() throws IOException {
       reader = new FileReader("src/main/java/com/example/test/settings.properties");
       properties = new Properties();
       properties.load(reader);
    }

    public String readProprieties(String var) {
        return properties.getProperty(var);
    }

    public void changeSetting(String field,String value) {
        properties.setProperty(field,value);
        var string = properties.toString().replace("{","").replace("}","")
                .replace(", ","\n");
        System.out.println(string);
        try(FileWriter writter = new FileWriter("src/main/java/com/example/test/settings.properties")) {

            writter.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
