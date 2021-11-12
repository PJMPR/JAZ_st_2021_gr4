package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class YieldDictionary {

    public static List<Dictionary> listOfElements() {
        return getElements();
    }

    private static List<Dictionary> getElements() {
        List<Dictionary> items = new ArrayList<>();
        Path filePath = Paths.get("C:\\Users\\marek\\Documents\\GitHub\\JAZ_st_2021_gr4\\lab04\\dictionaries.csv");
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Dictionary item = createElement(attributes);
                items.add(item);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    private static Dictionary createElement(String[] fields) {
        return new Dictionary(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], fields[3], fields[4]);
    }
}
