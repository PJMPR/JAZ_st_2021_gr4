package org.example.caching.loaders;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.caching.Cache;
import org.example.model.Dictionary;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DictionaryCacheLoader implements CachLoader {
    private final Cache cache;
    public DictionaryCacheLoader(){
        this.cache=Cache.getInstance();
    }
    @Override
    public void load()  {
        try (CSVReader csvReader = new CSVReader(new FileReader("dictionaries.csv"));) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                List<String> list2 = Arrays.asList(values);
                cache.add("dictionaries", new Dictionary(Integer.parseInt((list2.get(0))),Integer.parseInt((list2.get(1))),list2.get(2),list2.get(3),list2.get(4)));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
