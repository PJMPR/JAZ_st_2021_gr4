package org.example.caching;

public class CacheItem<T> {
    private final T item;
    private final String key;

    public CacheItem(String key, T item) {
        this.key = key;
        this.item = item;
    }

    public String getKey() {
        return key;
    }

    public T getItem() {
        return item;
    }
}