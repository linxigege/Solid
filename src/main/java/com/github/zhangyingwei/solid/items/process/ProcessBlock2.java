package com.github.zhangyingwei.solid.items.process;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.items.Block2;

import java.util.ArrayList;
import java.util.List;

public abstract class ProcessBlock2 implements Block2 {

    protected String leftMark = Constants2.PROCESS_LEFTMARK;
    protected String rightMark = Constants2.PROCESS_RIGHTMARK;
    protected String tag;
    protected String endTag;
    protected String topMark;
    protected List<Block2> childBlocks = new ArrayList<>();
    protected SolidContext2 context2;
    protected boolean flag = true;
    private boolean deleteBlank = false;

    public ProcessBlock2(String topMark, SolidContext2 context2) {
        this.topMark = topMark;
        this.context2 = context2;
        this.checkWhiteTag();
    }

    /**
     * check if {% process %}
     * is make tag and remove it
     */
    private void checkWhiteTag() {
        if (this.topMark.startsWith(this.leftMark.concat("-")) && this.topMark.endsWith("-".concat(this.rightMark))) {
            this.deleteBlank = true;
            String[] marks = this.topMark.split(" ");
            marks[0] = this.leftMark;
            marks[marks.length - 1] = this.rightMark;
            this.topMark = String.join(" ", marks);
        }
    }

//    protected
}