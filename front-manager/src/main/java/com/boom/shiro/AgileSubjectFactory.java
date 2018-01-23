package com.boom.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 12:59
 * @Description 扩展自DefaultWebSubjectFactory,对于无状态的TOKEN 类型不创建session
 */
public class AgileSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        AuthenticationToken token = context.getAuthenticationToken();
        if((token instanceof HmacToken)){
            // 当token为HmacToken时， 不创建 session
            context.setSessionCreationEnabled(false);
        }
        return super.createSubject(context);
    }
}
