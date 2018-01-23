package com.boom.shiro;

import com.boom.utils.JWTUtils;
import com.github.pagehelper.util.StringUtil;
import com.summer.base.utils.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/23 下午10:27
 * @Description
 */
public class TokenFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);
    private static final String ACCESS_TOKEN = "access_token";

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) throws Exception {
        if (getSubject(request,response).isAuthenticated() || validateAccessToken(request)) {
            log.warn("continue to access");
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isTokenAccess(request)) {
            log.warn("TokenFilter.onAccessDenied()");
            return validateAccessToken(request);
        }
        throw new UnauthenticatedException("未授权");
    }

    protected boolean validateAccessToken(ServletRequest request) {
        boolean pass = false;
        String accessToken = request.getParameter(ACCESS_TOKEN);

        if (StringUtils.isNotEmpty(accessToken)) {
            String username = JWTUtils.getUsername(accessToken);
            if (StringUtil.isNotEmpty(username)) {
                pass = JWTUtils.verify(accessToken,username,JWTUtils.SECRET);
            }
        }

        return pass;
    }

    protected boolean isTokenAccess(ServletRequest request) {
        return (request instanceof HttpServletRequest) &&
                StringUtils.isNotEmpty(request.getParameter(ACCESS_TOKEN));
    }
}
