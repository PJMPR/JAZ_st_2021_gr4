package org.example.caching;

import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DictionaryProvider {

    public List<Dictionary> database() {
        String file = "dictionaries.csv";
        return readItems(file);
    }

    public String file() {
        File file = new File("dictionaries.csv");
        return file.getName()
                .replaceFirst("[.][^.]+$", "");
    }

    private static Dictionary makeItem(String[] element) {
        int id = Integer.parseInt(element[0]);
        int intKey = Integer.parseInt(element[1]);
        String stringKey = element[2];
        String value = element[3];
        String dictionaryName = element[4];
        return new Dictionary(id, intKey, stringKey, value, dictionaryName);
    }

    private static List<Dictionary> readItems(String file) {
        List<Dictionary> dictionaries = new ArrayList<>();
        Path path = Paths.get(file);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            while (line != null) {
                String[] params = line.split(",");
                Dictionary item = makeItem(params);
                dictionaries.add(item);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionaries;
    }
}
