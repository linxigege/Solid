package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.result.SolidResult2;
import com.github.zhangyingwei.solid.result.StringResult2;

public class EndProcessBlock2 extends ProcessBlock2 {

    private String endMark;

    public EndProcessBlock2(String topMark, SolidContext2 context2) {
        super(topMark, context2);
    }

    @Override
    public Block2 setFlag(boolean flag) {
        return null;
    }

    @Override
    public SolidResult2 render() {
        return new StringResult2("");
    }

    public boolean isEndOf(ProcessBlock2 processBlock2) {
        return processBlock2.getEndTag().equals(this.getTag());
    }
}