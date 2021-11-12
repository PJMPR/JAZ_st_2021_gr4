package org.example.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetDictionary {

    public static List<Dictionary> listOfElements() {
        return getElements();
    }

    private static List<Dictionary> getElements() {
        List<Dictionary> elements = new ArrayList<>();
        Path filePath = Paths.get("src/main/java/org/example/dictionaries.csv");
        try (Scanner scanner = new Scanner(filePath)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
                Dictionary item = createElement(attributes);
                elements.add(item);
                System.out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elements;
    }

    private static Dictionary createElement(String[] fields) {
        return new Dictionary(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], fields[3], fields[4]);
    }
}
