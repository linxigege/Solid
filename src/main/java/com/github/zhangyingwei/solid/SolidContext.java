package com.github.zhangyingwei.solid;

import com.github.zhangyingwei.solid.config.SolidTemplateResourcesLoader;
import com.github.zhangyingwei.solid.items.pipline.SolidMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class SolidContext {
    private Map<String, Object> params = new HashMap<String, Object>();
    private Map<String, SolidMethod> methodMap = new HashMap<String, SolidMethod>();
    private SolidTemplateResourcesLoader resourcesLoader;

    public SolidContext() {
    }

    public SolidContext(SolidTemplateResourcesLoader resourcesLoader) {
        this.resourcesLoader = resourcesLoader;
    }

    public void bindArgs(String key, Object value) {
        params.put(key, value);
    }

    public void bindMethod(String key, SolidMethod method) {
        this.methodMap.put(key, method);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public SolidMethod getMethod(String key) {
        return methodMap.get(key);
    }

    public void unbindArgs(String itemName) {
        this.params.remove(itemName);
    }

    public SolidTemplateResourcesLoader getResourcesLoader() {
        return resourcesLoader;
    }

    public void setResourcesLoader(SolidTemplateResourcesLoader resourcesLoader) {
        this.resourcesLoader = resourcesLoader;
    }

    public Map<String, SolidMethod> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, SolidMethod> methodMap) {
        this.methodMap = methodMap;
    }
}
