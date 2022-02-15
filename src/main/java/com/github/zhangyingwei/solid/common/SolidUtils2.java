package com.github.zhangyingwei.solid.common;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.config.FileTemplateResourceLoader2;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;
import com.github.zhangyingwei.solid.items.Block;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.items.process.ForProcessBlock;
import com.github.zhangyingwei.solid.template.TemplateResolver2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String formateAsNomal(String content) {
        return removeExtraSpaces(content.trim());
    }

    private static String removeExtraSpaces(String content) {
        while (content.indexOf("  ") >= 0) {
            content = content.replaceAll("  ", " ");
        }
        return content;
    }

    public static String subMarkToTemplate(String mark, String leftMark, String rightMark) {
        return mark.substring(leftMark.length(), (mark.length() - rightMark.length()));
    }

    public static Block2 routeProcessBlock(String template,SolidContext2 context2){
        List<String> commandItemList = Arrays.stream(template.trim().split(" ")).collect(Collectors.toList());
        commandItemList.remove(0);
        commandItemList.remove(commandItemList.size()-1);
        String command = String.join(" ", commandItemList);

        if (command.startsWith(Constants2.TAG_FOR)){
            return new ForProcessBlock()
        }
    }
}
