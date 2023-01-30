package by.vitstep.organizer.web;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hello")
    public String hello(String name, HttpServletResponse response) throws TemplateException, IOException{
        Configuration cfg=new Configuration(Configuration.VERSION_2_3_27);
        final ClassTemplateLoader loader=new ClassTemplateLoader(TestController.class,"/ftl");
        cfg.setTemplateLoader(loader);
        cfg.setDefaultEncoding("UTF-8");

        //модель данных
    }
}
