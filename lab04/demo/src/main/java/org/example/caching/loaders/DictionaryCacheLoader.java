package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.caching.DictionaryFilesProvider;
import org.example.model.Dictionary;
import java.util.List;

public class DictionaryCacheLoader implements CacheLoader{

    public final Cache cache = Cache.getInstance();

    public void load(){

        DictionaryFilesProvider provider = new DictionaryFilesProvider();
        List<Dictionary> records = provider.database();
        String file = provider.file();
        records.forEach(record -> cache.add(file, record));

    }
}