package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.SolidUtils;
import com.github.zhangyingwei.solid.result.SolidResult;
import com.github.zhangyingwei.solid.result.StringResult;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class RawProcessBlock extends ProcessBlock {

    public RawProcessBlock(String topMark, SolidContext context) {
        super(SolidUtils.formateAsNomal(topMark), context);
        super.tag = Constants.TAG_RAW;
        super.endTag = Constants.TAG_RAW_END;
    }

    @Override
    public SolidResult render() {
        return new StringResult(super.text());
    }
}
