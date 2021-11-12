package org.example.caching;

public class CachedElement {
    private String key;
    private Object element;

    public CachedElement(String key, Object element) {
        this.key = key;
        this.element = element;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
}
