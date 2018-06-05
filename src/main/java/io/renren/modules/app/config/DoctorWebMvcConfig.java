package io.renren.modules.app.config;

import io.renren.modules.app.interceptor.AuthorizationInterceptor;
import io.renren.modules.app.interceptor.DoctorAuthorizationInterceptor;
import io.renren.modules.app.resolver.DoctorUserHandlerMethodArgumentResolver;
import io.renren.modules.app.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * MVC配置
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2017-04-20 22:30
 */
@Configuration
public class DoctorWebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private DoctorAuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private DoctorUserHandlerMethodArgumentResolver doctorUserHandlerMethodArgumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/do/**").addPathPatterns("/app/d/**");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(doctorUserHandlerMethodArgumentResolver);
    }
}