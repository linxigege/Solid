package com.github.zhangyingwei.solid.items;

import com.github.zhangyingwei.solid.result.SolidResult2;

public interface Block2 {

    Block2 setFlag(boolean flag);

    SolidResult2 render();

    String text();
}