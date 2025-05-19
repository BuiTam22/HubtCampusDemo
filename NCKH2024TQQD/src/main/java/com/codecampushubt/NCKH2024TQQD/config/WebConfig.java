package com.codecampushubt.NCKH2024TQQD.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.codecampushubt.NCKH2024TQQD.config.advice.CloudinaryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CloudinaryProperties cloudinaryProperties;

    public WebConfig (CloudinaryProperties cloudinaryProperties) {
        this.cloudinaryProperties = cloudinaryProperties;
    }
    @Bean
    public ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");  // Đường dẫn đến thư mục chứa file .html
        resolver.setSuffix(".html");       // Định dạng file
        resolver.setTemplateMode("HTML");  // Kiểu template
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);      // Tắt cache khi phát triển
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
           "cloud_name", cloudinaryProperties.getCloudName(),
                "api_key",cloudinaryProperties.getApiKey(),
                "api_secret" , cloudinaryProperties.getApiSecret(),
                "secure",true
        ));
   }

        // Cấu hình đường dẫn cho CSS, JS, Images
        // @Override
        // public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //     registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        //     registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        // }

}
