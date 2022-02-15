package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.common.Constants;
import com.github.zhangyingwei.solid.common.Constants2;
import com.github.zhangyingwei.solid.common.SolidUtils2;
import com.github.zhangyingwei.solid.common.StringConveyor2;
import com.github.zhangyingwei.solid.config.SolidConfiguration2;
import com.github.zhangyingwei.solid.items.Block2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template2 implements SolidTemplate2 {

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