package org.example.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cache {

    private static final Cache cache = new Cache();
    private final List<CachedElement> cachedElements = new ArrayList<>();

    public static Cache getInstance() {
        return cache;
    }


    public <T> void add(String key, T element) {
        CachedElement cachedElement = new CachedElement(key, element);
        cachedElements.add(cachedElement);
    }

    public <T> T get(String key, Class<T> clazz) {
        return (T) cachedElements
                .stream()
                .filter(
                        cachedItem -> Objects
                                .equals(cachedItem
                                        .getKey(),key))
                .findAny()
                .get()
                .getElement();

    }

    public Object get(String key) {
        System.out.println(cachedElements.stream().filter(cacheItem -> key.equals(cacheItem.getKey())).map(CachedElement::getElement).collect(Collectors.toList()));
        return cachedElements
                .stream()
                .filter(cachedElement -> key
                        .equals(cachedElement
                                .getKey()))
                .map(CachedElement::getElement)
                .collect(Collectors.toList()).get(0);

    }
}
