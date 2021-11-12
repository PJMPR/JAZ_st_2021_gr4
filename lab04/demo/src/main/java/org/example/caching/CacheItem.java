package org.example.caching;

import java.util.Date;

public record CacheItem<T>(String setOfData, T data, Date expirationTime) {

    public Date getExpiration() {
        return expirationTime;
    }
    public String getDataSet() {
        return setOfData;
    }

    public T getData() {
        return data;
    }
}