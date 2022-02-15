package com.github.zhangyingwei.solid.config;

public interface SolidTemplateResourcesLoader2 {

    String load(String source);

    String getPrefix();

    void setPrefix(String prefix);

    String getSuffix();

    void setSuffix(String suffix);

    String getBasePath();
}