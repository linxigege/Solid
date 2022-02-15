package com.github.zhangyingwei.solid.items.pipline;

import com.github.zhangyingwei.solid.SolidContext;
import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.StringConveyor2;
import com.github.zhangyingwei.solid.items.Block;
import com.github.zhangyingwei.solid.result.SolidResult;

import java.util.ArrayList;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class PiplineBlock2 implements Block {
    private final SolidContext2 context;
    protected Object input;
    private String methodName;
    private String[] args;

    public PiplineBlock2(String methodName, SolidContext2 context) {
        this.methodName = this.getMethod(methodName).trim();
        String argsTemplate = methodName.replaceAll(this.methodName, "").trim();
        if (this.methodName.endsWith(":")) {
            this.args = this.splitArgs(argsTemplate);
        }else{
            this.args = new String[0];
        }
        this.context = context;
    }

    private String getMethod(String methodName) {
        return methodName.trim().split(" ")[0];
    }

    private String[] splitArgs(String argsTemplate) {
        ArrayList<String> resultArgs = new ArrayList<>();
        StringConveyor2 conveyor2 = new StringConveyor2(argsTemplate).trimLeft();
        while (conveyor2.length() > 0) {
            String result = "";
            if (conveyor2.startWith("\"")) {
                result = conveyor2.getFromTo("\"", "\"").result();
                conveyor2.getUntil(",", true);
            } else {
                result = conveyor2.getUntil(",", true).result();
            }
            resultArgs.add(result.trim());
        }
        return resultArgs.toArray(new String[resultArgs.size()]);
    }

    @Override
    public Block setFlag(boolean flag) {
        return null;
    }

    @Override
    public SolidResult render() {
        return null;
    }

    @Override
    public String text() {
        return null;
    }
}
