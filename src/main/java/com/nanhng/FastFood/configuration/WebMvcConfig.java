package com.nanhng.FastFood.configuration;

import com.nanhng.FastFood.security.interceptor.UserInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final UserInterceptor userInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/api/v*/**")
                .excludePathPatterns("/api/v*/auth/**",
                        "/api/v*/media/**");
    }
}
