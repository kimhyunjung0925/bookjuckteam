package com.project.bookjuck;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // "/" 경로로 접속 시 자동으로 "/main" 페이지로 이동하도록 설정
        registry.addViewController("/").setViewName("/main");
    }
}
