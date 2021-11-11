package org.example.caching;

import org.example.caching.loaders.DictionaryProvider;
import org.example.model.Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileProvider implements DictionaryProvider {
    private String line = "";
    private String comma = ",";
    private List<Dictionary> dictionaryList = new ArrayList<>();

    @Override
    public List<Dictionary> provide(String fileName){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

                    while ((line = bufferedReader.readLine()) != null){
                        String[] data = line.split(comma);
                        int id = Integer.parseInt(data[0]);
                        int key = Integer.parseInt(data[1]);
                        Dictionary dictionary = new Dictionary(id,key,data[2],data[3],data[4]);
                        dictionaryList.add(dictionary);
                    }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionaryList;
    }
}
