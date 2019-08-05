package cn.kang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import javax.annotation.PostConstruct;

/*
如果需要将Thymeleaf的html文件和java代码分离,可以通过下面这种方式
 */

//@Configuration
public class ThymeleafConfig {
    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @PostConstruct
    public void templateResolver() {
        UrlTemplateResolver resolver = new UrlTemplateResolver();
        resolver.setTemplateMode("HTML5");
        resolver.setPrefix("http://127.0.0.1/resource/");
        resolver.setSuffix(".html");
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        springTemplateEngine.setTemplateResolver(resolver);
    }
}
