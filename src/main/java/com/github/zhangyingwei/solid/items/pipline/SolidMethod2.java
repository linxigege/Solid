package com.github.zhangyingwei.solid.items.pipline;

public interface SolidMethod2<U> {

    <T> T doFormate(U content, Object... args);
}