package com.github.zhangyingwei.solid.cache;

import java.util.concurrent.ConcurrentHashMap;

public class CacheBuilder2 {

    private static ConcurrentHashMap<String, SolidCache2> cacheMap = new ConcurrentHashMap<>();

    public static SolidCache2 getOrCreateCache(String key) {
        if (!cacheMap.contains(key)) {
            cacheMap.put(key, new SolidCache2());
        }
        return cacheMap.get(key);
    }
}
