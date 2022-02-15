package com.github.zhangyingwei.solid.result;

public class StringResult2<String> implements SolidResult2 {

    private String result;

    public StringResult2(String result){
        this.result = result;
    }

    @Override
    public String getResult() {
        return result;
    }
}