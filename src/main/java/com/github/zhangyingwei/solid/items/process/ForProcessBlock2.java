package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.exception.SolidParamNotFoundException;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.result.SolidResult2;
import com.github.zhangyingwei.solid.result.StringResult2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ForProcessBlock2 extends ProcessBlock2 {

    private String itemName;
    private String sourcesName;
    private Object sources;

    public ForProcessBlock2(String topMark, SolidContext2 context2) {
        super(topMark, context2);
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
    public Block2 setFlag(boolean flag) {
        return null;
    }

    @Override
    public SolidResult2 render() {
        if (!flag) {
            return new StringResult2("");
        }
        SolidResult2<Object> sourcesResult = SolidUtils2.getFromPlaceHolderOrNot(super.context2, sourcesName);
        this.sources = sourcesResult.getResult();
        List<SolidResult2> childs = new ArrayList<>();
        if (this.sources instanceof Collection) {
            Collection collection = (Collection) this.sources;
            for (Object object : collection) {
                context2.bindArgs(this.itemName, object);
                childs.addAll(super.childsResult(true));
                context2.unbindArgs(this.itemName);
            }
        } else if (this.sources.getClass().isArray()) {
            Object[] objects = (Object[]) this.sources;
            for (Object object : objects) {
                context2.bindArgs(this.itemName, object);
                childs.addAll(super.childsResult(true));
                context2.unbindArgs(this.itemName);
            }
        } else {
            try {
                throw new SolidParamNotFoundException(sourcesName + "is not collection or array");
            } catch (SolidParamNotFoundException e) {
                e.printStackTrace();
            }
        }

        StringBuilder sBuilder = new StringBuilder();
        childs.stream().forEach(child -> {
            sBuilder.append(child.getResult());
        });

        return new StringResult2(sBuilder.toString());
    }
}