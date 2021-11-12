package org.example.caching.loaders;


import org.example.caching.Cache;
import org.example.caching.DictionaryProvider;
import org.example.model.Dictionary;

import java.util.List;

public class DictionaryCacheLoader implements CacheLoader{

    public final Cache cache = Cache.getInstance();

    @Override
    public void load() {
        DictionaryProvider provider = new DictionaryProvider();
        List<Dictionary> records = provider.database();
        String file = provider.file();
        records.forEach(record -> cache.add(file, record));
    }
}
