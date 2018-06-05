package io.renren.modules.app.resolver;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.annotation.MemberLoginUser;
import io.renren.modules.app.interceptor.AuthorizationInterceptor;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2017-03-23 22:02
 */
@Component
public class MemberUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(MemberEntity.class) && parameter.hasParameterAnnotation(MemberLoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }
        Wrapper<MemberEntity> wrapper= new EntityWrapper<>();
        wrapper.eq("user_id",(Long)object);
        MemberEntity memberEntity = memberService.selectOne(wrapper);
        if(memberEntity == null){
            throw new RRException("失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }

        return memberEntity;
    }
}
