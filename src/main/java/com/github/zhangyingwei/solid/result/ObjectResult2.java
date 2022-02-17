package com.github.zhangyingwei.solid.result;

public class ObjectResult2<Object> implements SolidResult2 {

    private Object result;

    public ObjectResult2(Object result) {
        this.result = result;
    }

    @Override
    public Object getResult() {
        return result;
    }
}