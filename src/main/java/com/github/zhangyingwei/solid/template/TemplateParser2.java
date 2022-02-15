package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.SolidContext2;
import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.items.object.ObjectBlock;
import com.github.zhangyingwei.solid.items.object.ObjectBlock2;
import com.github.zhangyingwei.solid.items.text.TextBlock;
import com.github.zhangyingwei.solid.items.text.TextBlock2;

import java.util.ArrayList;
import java.util.List;

public class TemplateParser2 {

    private SolidContext2 context2;

    public TemplateParser2(SolidContext2 context2) {
        this.context2 = context2;
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("{{user.name}}");
        System.out.println(builder.indexOf("{{"));
    }

    public List<Block2> parse(String content) {
        List<Block2> block2s = new ArrayList<>();
        TemplateFlow2 templateFlow2 = new TemplateFlow2(content);
        while (templateFlow2.isNotEmpty()){
            Block2 block2 = null;
            if (templateFlow2.startWith(Constants2.OBJ_LEFTMARK)){
                StringBuilder tempObj = new StringBuilder();
                while (!templateFlow2.startWith(Constants2.OBJ_RIGHTMARK)){
                    tempObj.append(templateFlow2.pull(1));
                }
                tempObj.append(templateFlow2.pull(Constants2.OBJ_RIGHTMARK.length()));
                block2 = new ObjectBlock2(this.context2,tempObj.toString());
            }else if (templateFlow2.startWith(Constants2.PROCESS_LEFTMARK)){
                StringBuilder tempObj = new StringBuilder();
                while (!templateFlow2.startWith(Constants2.PROCESS_RIGHTMARK)){
                    tempObj.append(templateFlow2.pull(1));
                }
                tempObj.append(templateFlow2.pull(Constants2.PROCESS_RIGHTMARK.length()));
                block2 = SolidUtils2.routeProcessBlock(tempObj.toString(),this.context2);
            }else{
                StringBuilder tempObj = new StringBuilder();
                while (!templateFlow2.startWith(Constants2.OBJ_LEFTMARK)&&!templateFlow2.startWith(Constants2.PROCESS_LEFTMARK)){
                    tempObj.append(templateFlow2.pull(1));
                }
                block2 = new TextBlock2(tempObj.toString());
            }
            block2s.add(block2);
        }
        return block2s;
    }

    public static class TemplateFlow2 {
        StringBuilder contentBuilder;

        public TemplateFlow2(String content) {
            this.contentBuilder = new StringBuilder(content);
        }

        public boolean startWith(String str) {
            return this.contentBuilder.indexOf(str) == 0;
        }

        public String pull(int length) {
            String result = contentBuilder.substring(0, length);
            contentBuilder.delete(0, length);
            return result;
        }

        public boolean isNotEmpty() {
            return this.contentBuilder.length() > 0;
        }
    }
}