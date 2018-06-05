package io.renren.modules.app.resolver;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.modules.app.annotation.DoctorLoginUser;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.interceptor.AuthorizationInterceptor;
import io.renren.modules.app.interceptor.DoctorAuthorizationInterceptor;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.ref.WeakReference;


/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2017-03-23 22:02
 */
@Component
public class DoctorUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(DoctorEntity.class) && parameter.hasParameterAnnotation(DoctorLoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(DoctorAuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }


        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", (Integer) object);
        //获取用户信息
        DoctorEntity doctorEntity = doctorService.selectOne(wrapper);

        if (doctorEntity == null) {
            return null;
        }
        return doctorEntity;
    }
}
