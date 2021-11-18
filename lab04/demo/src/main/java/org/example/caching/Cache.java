package org.example.caching;

import org.example.model.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cache {
    private static final Cache cache = new Cache();
    private  List<CacheObject> cacheObjects = new ArrayList<>();

    public static Cache getInstance(){
        return new Cache();
    }


    public <T> void add(String key, T item){
        CacheObject cacheObject = new CacheObject();
        cacheObject.item = item;
        cacheObject.key = key;
        cacheObjects.add(cacheObject);
    }

    public <T> T get(String key, Class<T> clazz){

        return (T) cacheObjects.stream()
                .filter(item->key.equals(item.key)
                        &&clazz.equals(item.item.getClass()))
                .findAny().get().item;
    }

    public Object get(String key){
        return cacheObjects.stream()
                .filter(cache -> key.equals(cache.key))
                .map(cache -> cache.item)
                .collect(Collectors.toList());
    }
}
