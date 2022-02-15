package com.github.zhangyingwei.solid.items.object;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.items.pipline.PiplineBlock2;
import com.github.zhangyingwei.solid.result.SolidResult2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectBlock2 implements Block2 {
    private String leftMark = Constants.OBJ_LEFTMARK;
    private String rightMark = Constants.OBJ_RIGHTMARK;
    private String template;
    private String templateContent;
    private SolidContext2 context;
    private boolean flag = true;
    private String key;
    private List<PiplineBlock2> piplines;

    public ObjectBlock2(SolidContext2 context2, String template) {
        this.template = template;
        this.context = context2;
        this.init();
    }

    private void init() {
        this.template = SolidUtils2.formateAsNomal(this.template);
        this.templateContent = SolidUtils2.subMarkToTemplate(template.trim(), leftMark, rightMark);
        List<String> items = Arrays.stream(this.templateContent.split("\\|")).collect(Collectors.toList());
        this.key = items.remove(0).trim();
        this.piplines = this.buildPipLines(items);
    }

    private List<PiplineBlock2> buildPipLines(List<String> items) {
        return items.stream().map(item -> new PiplineBlock2(item, context)).collect(Collectors.toList());
    }

    @Override
    public Block2 setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    @Override
    public SolidResult2 render() {
        return null;
    }

    @Override
    public String text() {
        return null;
    }
}