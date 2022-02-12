package com.github.zhangyingwei.solid.items.pipline;

import java.util.Locale;

public class CapitalizeSolidMethod2 implements SolidMethod2<String> {
    @Override
    public String doFormate(String content, Object... args) {
        return content.substring(0, 1).toUpperCase().concat(content.substring(1).toLowerCase(Locale.ROOT));
    }
}