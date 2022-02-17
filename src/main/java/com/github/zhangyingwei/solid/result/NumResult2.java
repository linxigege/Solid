package com.github.zhangyingwei.solid.result;

import java.math.BigDecimal;

public class NumResult2 implements SolidResult2 {

    private BigDecimal result;

    public NumResult2(String result) {
        this.result = new BigDecimal(result);
    }

    @Override
    public Object getResult() {
        return null;
    }
}