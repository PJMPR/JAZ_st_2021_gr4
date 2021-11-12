package org.example.caching.loaders;



import org.example.caching.Cache;

import java.io.FileNotFoundException;

public interface CachLoader {

    void load() throws FileNotFoundException;
}
