package com.github.zhangyingwei.solid.result;

public class WowResult2 implements SolidResult2<String> {

    private String result = "wow";
    private String key;

    public WowResult2(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String getResult() {
        return null;
    }
}