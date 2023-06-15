package com.example.productthymeleaf.config;

import com.example.productthymeleaf.service.ICRUDService;
import com.example.productthymeleaf.service.ProductService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.IOException;

@Configuration //annotation đánh dấu đây là file config
@EnableWebMvc //annotatino cho phép sử dụng các thành phần của SpringMVC
@ComponentScan(basePackages = "com.example.productthymeleaf")
@PropertySource("classpath:upload.properties")
//annotation cho phép chỉ định tài nguyên tĩnh của dự án
public class AppConfiguration implements WebMvcConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Value("${path-upload}") //annotation cho phép truy xuất tài nguyên tĩnh thông qua name của nó
    private String upload;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //Cấu hình Thymleaf
    @Bean //annotation đánh dấu khai báo 1 bean, để được quản lý bởi IoCContainer
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/products");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public ICRUDService productService() {
        return new ProductService();
    }

    @Override
    //phương thức đăng ký tài nguyên tĩnh của dự án
    //ResourceHandler dùng để tạo source định danh thay thế
    //ResourceLocation dùng để xác định source thực tế của tài nguyên
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:" + upload);
    }

    //Bean kiểm soát việc nhận file của Server, kích thước tối đa hiện tại là khoảng 5Mb
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800);
        return resolver;
    }
}
