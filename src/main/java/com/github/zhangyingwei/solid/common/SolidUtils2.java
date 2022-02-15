package com.github.zhangyingwei.solid.common;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.config.FileTemplateResourceLoader2;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;
import com.github.zhangyingwei.solid.template.TemplateResolver2;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class SolidUtils2 {

    public static TemplateResolver2 layoutResolver(SolidContext2 fromContext) {
        FileTemplateResourceLoader2 resourceLoader2 = new FileTemplateResourceLoader2(
                fromContext.getResourcesLoader().getBasePath()
        );
        SolidContext2 context = new SolidContext2(resourceLoader2);
        context.setMethodMap(fromContext.getMethodMap());
        context.setParams(fromContext.getParams());
        SolidConfiguration2 configuration2 = new SolidConfiguration2(context);
        TemplateResolver2 resolver = new TemplateResolver2(configuration2);
        resolver.setPrefix(Constants.LAYOUT_BASE_PATH);
        resolver.setSuffix(fromContext.getResourcesLoader().getSuffix());
        return resolver;
    }
}
