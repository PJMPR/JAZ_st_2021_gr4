package org.example.caching.loaders;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.caching.Cache;
import org.example.model.Dictionary;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DictionaryCacheLoader {

    public void load(){
        String path = "src/main/resources/dictionaries.csv";
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> csvFile = reader.readAll();
            for (String[] row : csvFile){
                Cache.add("dictionaries",createDictionary(row));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private Object createDictionary(String[] row) {
        return new Dictionary(Integer.parseInt(row[0]), Integer.parseInt(row[1]),row[2],row[3],row[4]);
    }
}
