package com.github.zhangyingwei.solid.items.pipline;

public class AppendSolidMethod2 implements SolidMethod2<String> {
    @Override
    public String doFormate(String content, Object... args) {
        return content.concat(args[0].toString());
    }
}