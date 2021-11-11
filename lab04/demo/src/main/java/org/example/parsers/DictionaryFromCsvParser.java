package org.example.parsers;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.example.model.Dictionary;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DictionaryFromCsvParser {
    public List<Dictionary> parse(){
        try{
            CSVReader reader=
                    new CSVReaderBuilder(new FileReader("dictionaries.csv")).build();
            return reader
                    .readAll()
                    .stream()
                    .map(data->
                            new Dictionary(
                                    Integer.parseInt(data[0]),
                                    Integer.parseInt(data[1]),
                                    data[2],
                                    data[3],
                                    data[4]
                            )
                 ).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


