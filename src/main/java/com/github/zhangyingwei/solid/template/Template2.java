package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.common.*;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;
import com.github.zhangyingwei.solid.items.Block2;
import com.github.zhangyingwei.solid.items.process.ProcessBlock2;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import jdk.nashorn.internal.ir.BlockStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template2 implements SolidTemplate2 {

    private SolidConfiguration2 configuration2;
    private TemplateParser2 templateResolver2;
    private String source;
    private String contentType = Constants.CONTENT_TYPE;
    private List<Block2> resultBlocks;
    private Header2 header2;

    public Template2(SolidConfiguration2 configuration2,String source){
        this.configuration2 = configuration2;
        this.templateResolver2 = new TemplateParser2(this.configuration2.getContext());
        this.source = source;
    }

    @Override
    public void bind(String key, Object value) {
        this.configuration2.getContext().bindArgs(key,value);
    }

    private void init(){
        String content = this.configuration2.getResourcesLoader().load(source);
        StringConveyor2 stringConveyor2 = new StringConveyor2(content);
        String headerContent = stringConveyor2.getFromTo("---".concat(Constants2.Wrap()),"---".concat(Constants2.Wrap())).result();
        content = stringConveyor2.toString();
        this.header2 = new Header2(headerContent);
        List<Block2> block2s = this.templateResolver2.parse(content);
        for (int i= 0;i< block2s.size()-2;i++){
            Block2 before = block2s.get(i);
            Block2 current = block2s.get(i + 1);
            Block2 fater = block2s.get(i + 2);
            if (current instanceof ProcessBlock2){
                ProcessBlock2 currentProcess = (ProcessBlock2) current;
                if (currentProcess)
            }
        }
    }

    @Override
    public String render() {
        return null;
    }

    class Header2 {
        Map<String, String> params = new HashMap<String, String>();
        private String template;
        private TemplateResolver2 layoutResolver;

        Header2(String template) {
            this.template = template;
            this.layoutResolver = SolidUtils2.layoutResolver(configuration2.getContext());
            this.analysis();
        }

        private void analysis() {
            StringConveyor2 conveyor = new StringConveyor2(template);
            String content = conveyor.getBetween("---".concat(Constants2.Wrap()), "---".concat(Constants2.Wrap())).result();
            conveyor = new StringConveyor2(content);
            while (conveyor.length() > 0) {
                String key = conveyor.getUntil(":", false).result();
                conveyor.getUntil(":", true);
                String value = conveyor.getUntil(Constants2.Wrap(), false).result();
                conveyor.getUntil(Constants2.Wrap(), true);
                params.put(key, value);
            }
        }

        boolean available() {
            return !params.isEmpty() && params.containsKey("layout") && !"null".equals(params.get("layout"));
        }

        SolidTemplate2 template() {
            Map<String, String> page = new HashMap<>(params);
            page.remove("layout");
            // bind("page",page);
            page.entrySet().stream().forEach(e -> {
                bind(e.getKey(), e.getValue());
            });

            String layoutTemplateName = this.params.get("layout").trim();
            SolidTemplate2 resolve = layoutResolver.resolve(layoutTemplateName.trim());
            return resolve;
        }
    }
}