package org.example.caching;

import org.example.model.CacheRow;
import org.example.model.Dictionary;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cache {

    private static Cache instance;
    private static List<CacheRow> cacheDatabase = new ArrayList<>();

    private Cache() {

    }

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public static List<CacheRow> getCacheDatabase() {
        return cacheDatabase;
    }

    public static <T> void add(String key, T item){
        cacheDatabase.add(new CacheRow(key,item));
    }

    public <T> T get(String key, Class<T> clazz){
        return (T) cacheDatabase.stream()
                .filter(dbRow->dbRow.getKey()==key)
                .filter(dbRow ->dbRow.getObject().getClass()==clazz)
                .findAny().get().object;
    }

    public Object get(String key){
        return cacheDatabase.stream()
                .filter(dbRow ->dbRow.getKey()==key)
                .map(dbRow -> dbRow.getObject())
                .collect(Collectors.toList());
    }
}
