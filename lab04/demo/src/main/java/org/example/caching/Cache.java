package org.example.caching;

import org.example.model.CacheRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cache {

    private static Cache instance;
    private static final List<CacheRow> cacheDatabase = new ArrayList<>();

    private Cache() {

    }

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public static <T> void add(String key, T item){
        cacheDatabase.add(new CacheRow(key,item));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz){
        return (T) Objects.requireNonNull(cacheDatabase.stream()
                .filter(dbRow -> Objects.equals(dbRow.getKey(), key))
                .filter(dbRow -> dbRow.getObject().getClass() == clazz)
                .findAny().orElse(null)).object;
    }

    public Object get(String key){
        return cacheDatabase.stream()
                .filter(dbRow -> Objects.equals(dbRow.getKey(), key))
                .map(CacheRow::getObject)
                .collect(Collectors.toList());
    }
}
