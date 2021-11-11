package org.example.model;

public class CacheItem <T> {
    String key;
    T value;

    public CacheItem(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CacheItem{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
