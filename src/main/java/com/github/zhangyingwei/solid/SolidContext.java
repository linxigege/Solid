package com.github.zhangyingwei.solid;

import com.github.zhangyingwei.solid.items.pipline.SolidMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class SolidContext {
    private Map<String, Object> params = new HashMap<String, Object>();
    private Map<String, SolidMethod> methodMap = new HashMap<String,SolidMethod>();

    public void bindArgs(String key, Object value) {
        params.put(key, value);
    }

    public void bindMethod(String key, SolidMethod method) {
        this.methodMap.put(key, method);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public SolidMethod getMethod(String key) {
        return methodMap.get(key);
    }
}