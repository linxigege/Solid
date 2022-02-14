package com.github.zhangyingwei.solid.common;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.config.*;
import com.github.zhangyingwei.solid.exception.SolidException;
import com.github.zhangyingwei.solid.exception.SolidParamNotFoundException;
import com.github.zhangyingwei.solid.items.Block;
import com.github.zhangyingwei.solid.items.process.*;
import com.github.zhangyingwei.solid.items.text.TextBlock;
import com.github.zhangyingwei.solid.result.*;
import com.github.zhangyingwei.solid.template.TemplateResolver;
import com.github.zhangyingwei.solid.template.TemplateResolver2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class SolidUtils2 {

    public static TemplateResolver2 layoutResolver(SolidContext2 fromContext){
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
