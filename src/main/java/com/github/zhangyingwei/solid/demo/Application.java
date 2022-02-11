package com.github.zhangyingwei.solid.demo;

import com.github.zhangyingwei.solid.config.SolidConfiguration;
import com.github.zhangyingwei.solid.config.StringTemplateResourceLoader;
import com.github.zhangyingwei.solid.template.Template;
import com.github.zhangyingwei.solid.template.TemplateResolver;

/**
 * @author zhangyw
 * @date 2018/7/3
 */
public class Application {
    public static void main(String[] args) {
        User user = new User();
        user.setName("xiaoming");
        user.setAge(29);
        SolidConfiguration configuration = new SolidConfiguration(new StringTemplateResourceLoader());
        TemplateResolver templateResolver = new TemplateResolver(configuration);
        Template template = templateResolver.resolve("hello my name is {{user.name}} and my age is {{user.age}}.");
        template.bind("user", user);
        System.out.println(template.render());
    }
}