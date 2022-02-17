package com.github.zhangyingwei.solid.common;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.config.FileTemplateResourceLoader2;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.items.process.*;
import com.github.zhangyingwei.solid.items.text.TextBlock2;
import com.github.zhangyingwei.solid.result.*;
import com.github.zhangyingwei.solid.template.TemplateResolver2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;
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

    public static Block2 routeProcessBlock(String template, SolidContext2 context2) {
        List<String> commandItemList = Arrays.stream(template.trim().split(" ")).collect(Collectors.toList());
        commandItemList.remove(0);
        commandItemList.remove(commandItemList.size() - 1);
        String command = String.join(" ", commandItemList);

        if (command.startsWith(Constants2.TAG_FOR)) {
            return new ForProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_FOR_END)) {
            return new EndProcessBlock(template, context2).setTag(Constants2.TAG_FOR_END);
        } else if (command.startsWith(Constants2.TAG_IF)) {
            return new IFProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_ELSE_IF)) {
            return new ElseProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_ELSE)) {
            return new ElseProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_IF_END)) {
            return new EndProcessBlock(template, context2).setTag(Constants2.TAG_IF_END);
        } else if (command.startsWith(Constants2.TAG_ASSIGN)) {
            return new AssignProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_INCLUDE)) {
            return new IncludeProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_RAW)) {
            return new RawProcessBlock(template, context2);
        } else if (command.startsWith(Constants2.TAG_RAW_END)) {
            return new EndProcessBlock(template, context2).setTag(Constants2.TAG_RAW_END);
        }
        return new TextBlock2("not find process block, return a text block");
    }

    public static SolidResult2 getFromPlaceHolderOrNot(SolidContext2 context2, String template) {
        if (template == null || template.length() == 0) {
            return new StringResult2("");
        } else if (isNum(template.trim())) {
            return new NumResult2(template);
        } else if (isPlaceholder(template)) {
            return getObjectFromContext(template, context2);
        } else {
            return new StringResult2(template.substring(1, template.length() - 1));
        }
    }

    public static Boolean isNum(String text) {
        Pattern pattern = Pattern.compile("[+\\-]?[0-9]*[.]?[0-9]*?");
        return pattern.matcher(text).matches();
    }

    private static Boolean isPlaceholder(String text) {
        char first = text.charAt(0);
        char last = text.charAt(text.length() - 1);
        return (first != '"' && last != '"') && (first != '\'' && last != '\'');
    }

    private static SolidResult2<Object> getObjectFromContext(String template, SolidContext2 context2) {
        StringConveyor2 conveyor2 = new StringConveyor2(template);
        String fatherTemplate = conveyor2.getUntil("[", false).result().trim();
        String childTemplate = conveyor2.getBetween("[", "]").result().trim();
        String[] objectKeys = fatherTemplate.split("\\.");
        Object tempValue = context2.getParams();
        for (String objectKey : objectKeys) {
            tempValue = getValueFromObject(tempValue, objectKey);
            if (tempValue instanceof WowResult2) {
                return (SolidResult2) tempValue;
            }
        }
        SolidResult2 result2 = (SolidResult2) tempValue;
        if (childTemplate != null && childTemplate.length() > 0) {
            if (result2.getResult().getClass().isArray()) {
                Object[] objects = (Object[]) result2.getResult();
                tempValue = new ObjectResult2(objects[Integer.parseInt(childTemplate.trim())]);
            } else if (tempValue instanceof Collection) {
                Collection collection = (Collection) result2.getResult();
                tempValue = new ObjectResult2(collection.toArray()[Integer.parseInt(childTemplate.trim())]);
            } else {
                tempValue = getValueFromObject(result2.getResult(), getFromPlaceHolderOrNot(context2, childTemplate).getResult().toString());
            }
        }
        return (SolidResult2<Object>) tempValue;
    }

    private static SolidResult2 getValueFromObject(Object object, String key) {
        if (object instanceof SolidResult2) {
            object = ((SolidResult2) object).getResult();
        }
        if (object instanceof Map) {
            if (((Map) object).containsKey(key)) {
                return new ObjectResult2(((Map) object).get(key));
            } else {
                return new WowResult2(key);
            }
        } else {
            return getFromObject(object, key);
        }
    }

    private static SolidResult2 getFromObject(Object object, String key) {
        if (object.getClass().isArray()) {
            Object[] objects = (Object[]) object;
            if ("first".equals(key)) {
                return new ObjectResult2(objects[0]);
            } else if ("last".equals(key)) {
                return new ObjectResult2(objects[objects.length - 1]);
            }
        }

        Class<? extends Object> clazz = object.getClass();
        String methodName = "get".concat(key.substring(0, 1).toUpperCase(Locale.ROOT).concat(key.substring(1).toLowerCase(Locale.ROOT)));

        try {
            Method method = clazz.getMethod(methodName);
            Object result = method.invoke(object);
            if (null == result) {
                return new WowResult2(key);
            }
            return new StringResult2<>(result.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return new WowResult2(key);
        }
    }

}
