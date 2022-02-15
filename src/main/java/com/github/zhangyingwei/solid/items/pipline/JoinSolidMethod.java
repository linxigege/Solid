package com.github.zhangyingwei.solid.items.pipline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 追加
 *
 * @author zhangyw
 * @date 2018/7/3
 */
public class JoinSolidMethod implements SolidMethod<Object> {
    @Override
    public String doFormate(Object content, Object[] args) {
        if (content.getClass().isArray()) {
            List<String> result = new ArrayList<>();
            result.addAll(Arrays.asList((String[]) content));
            return String.join(args[0].toString(), result);
        } else if (content instanceof Collection) {
            Collection input = (Collection) content;
            return String.join(args[0].toString(), input);
        }
        return "";
    }
}
