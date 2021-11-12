package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.model.Dictionary;
import org.example.model.GetDictionary;
import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader {

    Cache cache = Cache.getInstance();

    List<GetDictionary> getDictionaries;

    {
        getDictionaries = List.of(new GetDictionary());
    }

    public void load() {
        List<Dictionary> elements = new ArrayList<>();
        elements.addAll(GetDictionary.listOfElements());
        cache.add("dictionaries", elements);
    }
}
