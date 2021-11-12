package org.example.caching;

import java.util.Date;

public class CacheItem {
    private final long CreatedDate;
    private Object object;
    private String key;
    private long defaultExpiration = 7000;

    public long getCreatedDate() {
        return CreatedDate;
    }

    public long getDefaultExpiration() {
        return defaultExpiration;
    }

    public void setDefaultExpiration(long defaultExpiration) {
        this.defaultExpiration = defaultExpiration;
    }

    public long getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        ExpirationDate = expirationDate;
    }

    private long ExpirationDate;
    public CacheItem(Object obj, String key, Date startdate){
        this.CreatedDate=startdate.getTime();
        this.object=obj;
        this.key=key;
        this.ExpirationDate=startdate.getTime()+defaultExpiration;
    }
    public CacheItem(Object obj, String key, Date startdate, Long expiration){
        this.CreatedDate=startdate.getTime();
        this.object=obj;
        this.defaultExpiration=expiration;
        this.key=key;
        this.ExpirationDate=startdate.getTime()+expiration;
    }
    public CacheItem(Object obj, String key, Date startdate, Date expiration){
        this.CreatedDate=startdate.getTime();
        this.object=obj;
        this.defaultExpiration=expiration.getTime()- startdate.getTime();
        this.key=key;
        this.ExpirationDate=expiration.getTime();
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
