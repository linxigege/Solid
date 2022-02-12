package com.github.zhangyingwei.solid.config;

public class StringTemplateResourceLoader2 implements SolidTemplateResourcesLoader2{

    @Override
    public String load(String source) {
        return source;
    }

    @Override
    public void setPrefix(String prefix) {

    }

    @Override
    public void setSuffix(String suffix) {

    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String getBasePath() {
        return null;
    }
}