package com.github.zhangyingwei.solid;

import com.github.zhangyingwei.solid.config.SolidTemplateResourcesLoader2;
import com.github.zhangyingwei.solid.items.pipline.SolidMethod;
import com.github.zhangyingwei.solid.items.pipline.SolidMethod2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class SolidContext2 {

    private Map<String, Object> params = new HashMap<>();
    private Map<String, SolidMethod2> methodMap = new HashMap<>();

    private SolidTemplateResourcesLoader2 resourcesLoader;

    public SolidContext2() {
    }

    public SolidContext2(SolidTemplateResourcesLoader2 resourcesLoader) {
        this.resourcesLoader = resourcesLoader;
    }

    public void bindArgs(String key, Object value) {
        params.put(key, value);
    }

    public void bindMethod(String key, SolidMethod2 method) {
        this.methodMap.put(key, method);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public SolidMethod2 getMethod(String key) {
        return methodMap.get(key);
    }

    public void unbindArgs(String itemName) {
        this.params.remove(itemName);
    }

    public void setResourcesLoader(SolidTemplateResourcesLoader2 resourcesLoader) {
        this.resourcesLoader = resourcesLoader;
    }

    public SolidTemplateResourcesLoader2 getResourcesLoader() {
        return resourcesLoader;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void setMethodMap(Map<String, SolidMethod2> methodMap) {
        this.methodMap = methodMap;
    }

    public Map<String, SolidMethod2> getMethodMap() {
        return methodMap;
    }

}