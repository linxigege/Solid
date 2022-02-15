package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.Constants2;

public class ElsIFProcessBlock2 extends IFProcessBlock{

    public ElsIFProcessBlock2(String topMark, SolidContext context) {
        super(topMark, context);
        super.endTag = Constants2.TAG_ELSIF_END;
        super.tag = Constants2.TAG_ELSE_IF;
    }
}