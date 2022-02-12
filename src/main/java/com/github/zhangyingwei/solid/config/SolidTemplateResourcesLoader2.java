package com.github.zhangyingwei.solid.config;

public interface SolidTemplateResourcesLoader2 {

    String load(String source);

    void setPrefix(String prefix);

    void setSuffix(String suffix);

    String getPrefix();

    String getSuffix();

    String getBasePath();
}