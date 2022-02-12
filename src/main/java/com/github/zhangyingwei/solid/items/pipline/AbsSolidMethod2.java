package com.github.zhangyingwei.solid.items.pipline;

import java.math.BigDecimal;

public class AbsSolidMethod2 implements SolidMethod2<Object> {
    @Override
    public String doFormate(Object content, Object... args) {
        BigDecimal input = new BigDecimal(content.toString());
        return input.abs().toString();
    }
}