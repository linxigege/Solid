package com.github.zhangyingwei.solid.items.pipline;


public class PrependSolidMethod2 implements SolidMethod2<String> {

    @Override
    public String doFormate(String content, Object[] args) {
        return args[0].toString().concat(content);
    }
}