package org.example.caching;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Cache {
    private static final Cache cache = new Cache();
    private Cache(){
        this.cacheItems= new ArrayList<>();
    }
    //public HashMap<String, Object> cacheItems;
    public List<CacheItem> cacheItems;
    public static synchronized Cache getInstance(){
        return cache;
    }

    public synchronized <T> void add(String key, T item){
        cache.cacheItems.add(new CacheItem(item,key, new Date()));
    }
    public synchronized void clean(){
        cacheItems.clear();
    }

    public synchronized <T> T get(String key, Class<T> clazz){
        return clazz.cast(cacheItems.stream().filter(x -> Objects.equals(x.getKey(), key))
                .map(CacheItem::getObject)
                .parallel()
                .findAny()
                .orElse(null));

    }

    public synchronized Object get(String key){
        return cacheItems.stream().filter(x -> Objects.equals(x.getKey(), key)).map(CacheItem::getObject)
                .toList()
                ;
    }
}
