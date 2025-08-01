package com.bezzangss.sign.web.internal;

import com.bezzangss.sign.web.internal._interceptor.InternalWebInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
public class InternalWebMvcConfigurer extends WebMvcConfigurerAdapter {
    private final InternalWebInterceptor internalWebInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(internalWebInterceptor)
                .order(1)
                .addPathPatterns("/internal/**");
    }
}
