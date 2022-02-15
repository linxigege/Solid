package com.github.zhangyingwei.solid.items.text;

import com.github.zhangyingwei.solid.items.Block;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.result.SolidResult;
import com.github.zhangyingwei.solid.result.SolidResult2;
import com.github.zhangyingwei.solid.result.StringResult;
import com.github.zhangyingwei.solid.result.StringResult2;

public class TextBlock2 implements Block2 {

    private String text;
    private boolean flag = true;
    private boolean skip = false;

    public TextBlock2(String text){
        this.text = text;
    }

    @Override
    public Block2 setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public SolidResult2 render() {
        if(!flag){
            return new StringResult2("");
        }
        return new StringResult2(getText());
    }

    public String getText(){
        if (skip){
            return "";
        }
        return text;
    }

    @Override
    public String text() {
        return getText();
    }
}