package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.result.SolidResult;
import com.github.zhangyingwei.solid.result.StringResult;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class EndProcessBlock extends ProcessBlock {
    private String endMark;

    public EndProcessBlock(String endMark, SolidContext context) {
        super(endMark, context);
    }

    @Override
    public SolidResult render() {
        return new StringResult("");
    }

    public boolean isEndOf(ProcessBlock processBlock) {
        return processBlock.getEndTag().equals(this.getTag());
    }
}
