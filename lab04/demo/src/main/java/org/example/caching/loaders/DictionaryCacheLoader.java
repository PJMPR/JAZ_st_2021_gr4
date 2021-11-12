package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.model.Dictionary;
import org.example.model.YieldDictionary;
import java.util.ArrayList;
import java.util.List;

public class DictionaryCacheLoader {

    Cache cache = Cache.getInstance();

    List<YieldDictionary> yieldDictionaries;

    {
        yieldDictionaries = List.of(new YieldDictionary());
    }

    public void load() {
        List<Dictionary> elements = new ArrayList<>();

        for (YieldDictionary yieldDictionary : yieldDictionaries) {
            elements.addAll(YieldDictionary.listOfElements());
        }
        cache.add("dictionaries", elements);
    }
}
