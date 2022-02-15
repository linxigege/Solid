package com.github.zhangyingwei.solid.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class SolidCache2 {

    private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Long> cacheTimeout = new ConcurrentHashMap<>();

    public SolidCache2() {
        this.startCleaner();
    }

    public void cache(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        if (cacheTimeout.contains(key)) {
            if (isTimeOut(key)) {
                return null;
            } else {
                return cache.get(key);
            }
        } else {
            return cache.get(key);
        }
    }

    private boolean isTimeOut(String key) {
        return System.currentTimeMillis() > cacheTimeout.get(key);
    }

    public void cache(String key, Object value, long timeout) {
        cache.put(key, value);
        cacheTimeout.put(key, System.currentTimeMillis() + timeout);
    }

    private void startCleaner() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    cleanCache();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void cleanCache() {
        List<String> timeOutKeys = new ArrayList<>();
        for (Map.Entry<String, Long> entry : cacheTimeout.entrySet()) {
            Long out = entry.getValue();
            if (System.currentTimeMillis() > out) {
                timeOutKeys.add(entry.getKey());
            }
        }

        for (String timeOutKey : timeOutKeys) {
            cache.remove(timeOutKey);
            cacheTimeout.remove(timeOutKey);
        }
    }

}
