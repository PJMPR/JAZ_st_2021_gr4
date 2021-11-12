package org.example.caching.readers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private Scanner scanner;

    public FileHandler(String path) {
        try {
            scanner = new Scanner(Paths.get(path).toFile());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public List<String[]> splitElement(String delim) {
        List<String[]> items = new ArrayList<>();
        while (scanner.hasNext()) {
            items.add(scanner.nextLine().split(delim));
        }
        return items;
    }
}