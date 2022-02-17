package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.common.SolidUtils;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.result.SolidResult;
import com.github.zhangyingwei.solid.result.StringResult;

public class ForProcessBlock2 extends ProcessBlock {

    private String itemName;
    private String sourcesName;
    private Object sources;

    public ForProcessBlock2(String topMark, SolidContext context) {
        super(topMark, context);
        super.tag = Constants2.TAG_FOR;
        super.endTag = Constants.TAG_FOR_END;
        this.getNames(topMark);
    }

    private void getNames(String topMark) {
        String forName = SolidUtils2.subMarkToTemplate(super.topMark, super.leftMark, super.rightMark).trim();
        String[] itemAndObject = forName.replaceFirst("for", "").split("in");
        this.itemName = itemAndObject[0].trim();
        this.sourcesName = itemAndObject[1].trim();
    }

    @Override
    public SolidResult render() {
        if (!flag){
            return new StringResult("");
        }
        SolidUtils2.getF
    }
}