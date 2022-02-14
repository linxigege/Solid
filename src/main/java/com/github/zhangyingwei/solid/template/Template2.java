package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.SolidUtils;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.common.StringConveyor;
import com.github.zhangyingwei.solid.config.SolidConfiguration;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;
import com.github.zhangyingwei.solid.items.Block;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.items.process.EndProcessBlock;
import com.github.zhangyingwei.solid.items.process.ProcessBlock;
import com.github.zhangyingwei.solid.items.text.TextBlock;

import java.util.*;

public class Template2 implements SolidTemplate{

    private SolidConfiguration2 configuration2;
    private TemplateResolver2 templateResolver2;
    private String source;
    private String contentType = Constants.CONTENT_TYPE;
    private List<Block2> resultBlocks;
    private Header2 header2;

    @Override
    public void bind(String key, Object value) {

    }

    @Override
    public String render() {
        return null;
    }

    class Header2{
        Map<String,String> params = new HashMap<String,String>();
        private String template;
        private TemplateResolver2 layoutResolver;

        Header2(String template){
            this.template = template;
            this.layoutResolver = SolidUtils2.layoutResolver(configuration2.getContext());
            this.analysis();
        }

        private void analysis(){
            StringConveyor conveyor = new StringConveyor(template);
        }
    }
}