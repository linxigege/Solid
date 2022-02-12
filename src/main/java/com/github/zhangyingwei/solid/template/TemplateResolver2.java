package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.cache.CacheBuilder;
import com.github.zhangyingwei.solid.cache.SolidCache;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.config.SolidConfiguration;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;

public class TemplateResolver2 {

    private SolidConfiguration2 configuration;
    private SolidCache cache;
    private String contentType;

    public TemplateResolver2(SolidConfiguration2 configuration2){
        this.configuration = configuration2;
        if (Constants.TEMPLATE_CACHE){
            this.cache = CacheBuilder.getOrCreateCache(Constants.KEY_TEMPLATE_CACHE);
        }
    }
}