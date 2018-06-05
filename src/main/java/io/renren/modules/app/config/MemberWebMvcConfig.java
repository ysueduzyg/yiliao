package io.renren.modules.app.config;

import io.renren.modules.app.interceptor.DoctorAuthorizationInterceptor;
import io.renren.modules.app.interceptor.MemberAuthorizationInterceptor;
import io.renren.modules.app.resolver.DoctorUserHandlerMethodArgumentResolver;
import io.renren.modules.app.resolver.MemberUserHandlerMethodArgumentResolver;
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
public class MemberWebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private MemberAuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private MemberUserHandlerMethodArgumentResolver memberUserHandlerMethodArgumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/app/m/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(memberUserHandlerMethodArgumentResolver);
    }
}