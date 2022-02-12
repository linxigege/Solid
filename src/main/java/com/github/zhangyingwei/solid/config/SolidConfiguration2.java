package com.github.zhangyingwei.solid.config;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.items.pipline.AppendSolidMethod2;
import com.github.zhangyingwei.solid.items.pipline.ConcatSolidMethod;
import com.github.zhangyingwei.solid.items.pipline.PrependSolidMethod2;
import com.github.zhangyingwei.solid.items.pipline.SolidMethod2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zhangyw
 * @date 2018/7/4
 */
public class SolidConfiguration2 {

    private SolidContext2 context;

    public SolidConfiguration2(SolidTemplateResourcesLoader2 resourcesLoader) {
        this.context = new SolidContext2();
        this.context.setResourcesLoader(resourcesLoader);
        this.init();
    }

    private void init() {
        this.context.bindMethod("append", new AppendSolidMethod2());
        this.context.bindMethod("prepend", new PrependSolidMethod2());

    }

    public SolidConfiguration2(SolidContext2 context) {
        this.context = context;
    }

    public SolidConfiguration2 loadConfig(String filePath) {
        Properties properties = this.loadConfigProperties(filePath);
        return loadConfig(properties);
    }

    private SolidConfiguration2 loadConfig(Properties properties) {
        this.fillConfigOfConstants(properties, "template.suffix", Constants.TEMPLATE_SUFFIX);
        this.fillConfigOfConstants(properties, "template.prefix", Constants.TEMPLATE_PREFIX);
        return this;
    }

    private void fillConfigOfConstants(Properties properties, String key, String constants) {
        if (properties.containsKey(key)) {
            constants = properties.getProperty(key);
        }
    }

    private Properties loadConfigProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public SolidContext2 getContext() {
        return context;
    }

    public SolidTemplateResourcesLoader2 getResourcesLoader() {
        return this.context.getResourcesLoader();
    }

    public void bindMethod(String key, SolidMethod2 method) {
        this.context.bindMethod(key, method);
    }
}