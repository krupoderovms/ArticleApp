package com.krupoderov.spring.articles.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс, представляющий собой конфигурацию WebMvc
 * С помощью переопределения метода мы указываем расположение файлов проекта, в данной случае картинок(img)
 *
 * @version 1.0
 * @author Krupoderov Mikhail
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img");
    }
}
