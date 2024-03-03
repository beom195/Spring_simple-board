package com.beom.spring_simpleboard.config;

import com.beom.spring_simpleboard.interceptor.AuthInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .order(1)
                .addPathPatterns("/post/detail/{id}" ,"/post/edit/{id}", "/post/write")
                .excludePathPatterns("/", "/member/join", "/member/login", "/member/logout", "/css/**");
    }
}
