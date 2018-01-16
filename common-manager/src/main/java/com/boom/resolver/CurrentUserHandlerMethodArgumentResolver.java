package com.boom.resolver;

import com.boom.annotations.CurrentUser;
import com.boom.utils.SecurityUtil;
import com.boom.vo.Principal;
import com.summer.base.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author yangyang
 * @Date 2018/1/16
 * @Time 16:43
 * @Description
 */
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(CurrentUserHandlerMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        /**
         * @CurrentUser注释支持的参数类型,仅支持 {@see Principal}或{@link Integer}
         */
        if (clazz.isAssignableFrom(Principal.class) || clazz.isAssignableFrom(Integer.class)) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.getClass() == CurrentUser.class) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {

        /**
         * 当为{@link Principal}的时候，表示将当前登录用户信息注入参数中，
         * 当为{@link java.lang.Integer}，表示将当前用户id注入参数中。
         */
        Object result = null;
        if (SecurityUtil.isAuthenticated() || SecurityUtil.isRemembered()) {
            Class<?> clazz = parameter.getParameterType();
            Principal principal = SecurityUtil.getPrincipal();

            /**
             * 只有被认证过，才会将用户数据填充到有{@link CurrentUser}注释的方法参数 {
             * @link Principal}实体对象中
             */
            if (clazz.isAssignableFrom(Principal.class)) {
                result = principal;
            }

            if (clazz.isAssignableFrom(Integer.class)) {
                result = ObjectUtils.isNotNull(principal) ? principal.getId() : null;
            }
        }

        return result;
    }
}
