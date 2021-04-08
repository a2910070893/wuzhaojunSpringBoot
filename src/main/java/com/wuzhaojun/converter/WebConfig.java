package com.wuzhaojun.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要拦截的路径
        String[] addPathPatterns = {
                "/**"
        };
        //不需要拦截的路径
        String[] excludePathPatterns = {
                "/api/login/**",
                "/doc.html/**",
                "/swagger-resources/**",
                "/swagger-ui.html/**",
                "/v2/api-docs/**",
                "/webjars/**",
                "/error/**",
                "/blog/findIdBlog/**"

        };

        //addPathPatterns()添加拦截路径
        //excludePathPatterns() 添加不拦截的路径
        //添加注册登录拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html","/v2/api-docs/","/swagger-ui.html","/webjars/**","/error/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
