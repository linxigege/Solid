package com.github.zhangyingwei.solid.config;

/**
 * @author zhangyw
 * @date 2018/7/4
 */
public interface SolidTemplateResourcesLoader {
    String load(String source);

    String getPrefix();

    void setPrefix(String prefix);

    String getSuffix();

    void setSuffix(String suffix);

    String getBasePath();
}
