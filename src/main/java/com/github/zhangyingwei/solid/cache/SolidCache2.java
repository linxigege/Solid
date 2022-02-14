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

//    public void cache(String key,Object)

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

    private void cleanCache(){
        List<String> timeOutKeys = new ArrayList<>();
        for (Map.Entry<String, Long> entry : cacheTimeout.entrySet()) {
            long out = entry.getValue();
            if (System.currentTimeMillis()>out){
                timeOutKeys.add(entry.getKey());
            }
        }

        for (String timeOutKey : timeOutKeys) {
            cache.remove(timeOutKey);
            cacheTimeout.remove(timeOutKey);
        }
    }

}
