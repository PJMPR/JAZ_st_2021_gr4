package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class Cache {
    private static final Cache cache = new Cache();
    private List<CacheElements> cacheElements = new ArrayList<>();


    public static Cache getInstance(){
        return cache;
    }


    public <T> void add(String key, T item){
        CacheElements cache = new CacheElements();
        cache.key = key;
        cache.item = item;
        cacheElements.add(cache);
    }

    public <T> T get(String key, Class<T> clazz){

        return (T) cacheElements.stream()
                .filter(item->key.equals(item.key)
                        &&clazz.equals(item.item.getClass()))
                .findAny().get().item;
    }

    public Object get(String key){
        return cacheElements.stream()
                .filter(cache -> key.equals(cache.key))
                .findAny().get().item;
    }
}
