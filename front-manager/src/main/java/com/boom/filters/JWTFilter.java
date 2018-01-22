package com.boom.filters;

import com.boom.shiro.JWTToken;
import com.summer.base.utils.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/22
 * @Time 14:09
 * @Description token验证过滤
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private static final Logger loggger = LoggerFactory.getLogger(JWTFilter.class);

    private static final String AUTHORIZATION = "Authorization";


    private String getAuthorization(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getParameter(AUTHORIZATION);
        return authorization;
    }

    /**
     * 判断用户是否想要登录，
     * 检查 @see HttpServletRequest请求中是否有Authorization参数
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        loggger.info("Filter===================>JWTFilter.isLoginAttempt()");
        return StringUtils.isNotEmpty(getAuthorization(request));
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        loggger.info("Filter===================>JWTFilter.executeLogin()");

        String authorization = this.getAuthorization(request);
        JWTToken token = new JWTToken(authorization);
        //提交给Subject进行登录，如果登录失败会抛出异常
        getSubject(request,response).login(token);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        loggger.info("Filter===================>JWTFilter.isAccessAllowed()");

        if (isLoginAttempt(request, response)) {
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                response401(request, response);
            }
        }
        return false;
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            httpServletResponse.sendRedirect("/401");
        } catch (IOException e) {

        }
    }
}
