package org.example.caching;

import org.example.SafeCaster;
import org.example.model.CacheItem;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    List<CacheItem> cacheItems;

    private Cache() {
        cacheItems = new ArrayList<>();
    }

    private static class Holder {
        private static final Cache INSTANCE = new Cache();
    }

    public static Cache getInstance(){
        return Holder.INSTANCE;
    }


    public <T> void add(String key, T item){
        cacheItems.add(new CacheItem(key, item));
    }

    public <T> T get(String key, Class<T> clazz){
        return SafeCaster.cast(get(key), clazz);
    }

    public Object get(String key){
        return cacheItems.stream()
                .filter(cacheItem -> cacheItem.getKey().equals(key))
                .map(CacheItem::getValue)
                .findAny()
                .orElse(null);
    }
}
