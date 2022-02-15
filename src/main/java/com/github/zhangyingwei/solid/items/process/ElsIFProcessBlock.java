package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.common.Constants;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class ElsIFProcessBlock extends IFProcessBlock {

    public ElsIFProcessBlock(String topMark, SolidContext context) {
        super(topMark, context);
        super.endTag = Constants.TAG_ELSIF_END;
        super.tag = Constants.TAG_ELSE_IF;
    }
}
