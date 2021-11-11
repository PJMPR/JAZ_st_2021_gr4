package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.FileProvider;
import org.example.model.Dictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader implements CacheLoader{

    private FileProvider fileProvider = new FileProvider();
    private String fileName = "src/main/java/org/example/dictionaries/dictionaries.csv";
    private List<Dictionary> dictionaries = new ArrayList<>();


    @Override
    public void load(){
        dictionaries = fileProvider.provide(fileName);
        dictionaries.stream()
                .forEach(dictionary -> Cache.getInstance()
                        .add("dictionaries",dictionaries));
    }
}
