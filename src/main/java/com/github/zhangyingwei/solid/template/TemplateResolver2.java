package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.cache.CacheBuilder2;
import com.github.zhangyingwei.solid.cache.SolidCache2;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;

public class TemplateResolver2 {

    private SolidConfiguration2 configuration;
    private SolidCache2 cache;
    private String contentType;

    public TemplateResolver2(SolidConfiguration2 configuration2) {
        this.configuration = configuration2;
        if (Constants2.TEMPLATE_CACHE) {
            this.cache = CacheBuilder2.getOrCreateCache(Constants.KEY_TEMPLATE_CACHE);
        }
    }

    public Template2 resolve(String source) {
        Template2 template = null;
        if (Constants.TEMPLATE_CACHE) {
            template = null;
        }
        return template;
    }

    public void setPrefix(String prefix) {
        this.configuration.getResourcesLoader().setPrefix(prefix);
    }

    public void setSuffix(String suffix) {
        this.configuration.getResourcesLoader().setSuffix(suffix);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}