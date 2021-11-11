package org.example.caching.loaders;

import org.example.caching.Cache;
import org.example.parsers.DictionaryFromCsvParser;

public class DictionaryCacheLoader implements CacheLoader{
    private final Cache cache;

    public DictionaryCacheLoader() {
        this.cache = Cache.getInstance();
    }

    @Override
    public void load() {
        cache.add("dictionaries", new DictionaryFromCsvParser().parse());
    }
}
