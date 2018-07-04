package com.github.zhangyingwei.solid.template;

import com.github.zhangyingwei.solid.config.Configuration;
import com.github.zhangyingwei.solid.config.StringTemplateResourceLoader;
import com.github.zhangyingwei.solid.demo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhangyw
 * @date 2018/7/4
 */
public class TemplateTest {
    @Test
    public void render() throws Exception {
        User user = new User();
        user.setName("xiaoming");
        user.setAge(123);
        Configuration configuration = new Configuration(new StringTemplateResourceLoader());
        TemplateBuilder builder = new TemplateBuilder(configuration);
        Template template = builder.bulidTemplate("Hello my name is {{ user.name }} and my age is {{user.age }}. and my hobbies have {% for hobby in hobbies %} {{ hobby }} {% endfor %} ");
        template.bind("user", user);
        System.out.println(template.render());
    }
}