package com.codingduo.cinemabookingticket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ResourceImageConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("./public");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/public/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
