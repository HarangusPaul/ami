package com.example.test.util.dataTypes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SavingFunctions {
    public static <T> void saveArrayListToFile(List<T> list, String filePath) {
        List<String> stringList = new ArrayList<>();
        for (T item : list) {
            stringList.add(item.toString());
        }

        Path path = Paths.get(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (String line : stringList) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    List<Entity> entities = new ArrayList<>();
//            entities.add(new Entity(1));
//            SavingFunctions.saveArrayListToFile(entities, "src/java");
}
