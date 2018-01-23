package com.boom.shiro;

import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 12:43
 * @Description 基于HMAC（ 散列消息认证码）的无状态认证过滤器
 */
public class HmacFilter extends AccessControlFilter {

    private static final Logger log = LoggerFactory.getLogger(HmacFilter.class);

    public static final String DEFAULT_CLIENTID_PARAM = "clientId";
    public static final String DEFAULT_TIMESTAMP_PARAM = "timeStamp";
    public static final String DEFAUL_DIGEST_PARAM = "digest";


    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) throws Exception {
        log.info("Filter:验证是否放行，继续访问=============>HmacFilter.isAccessAllowed()");
        Subject subject = getSubject(request, response);
        if (ObjectUtils.isNotNull(subject) && subject.isAuthenticated()) {
            log.info("放行，继续访问..");
            //已登录，放行
            return true;
        }
        //拒绝访问
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //如果是Hmac鉴权的请求,再做处理
        if (isHmacSubmission(request)) {
            AuthenticationToken token = createToken(request,response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                log.error(e.getMessage(),e);
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        return false;
    }

    protected AuthenticationToken createToken(ServletRequest request,ServletResponse response) {
        String clientId = request.getParameter(DEFAULT_CLIENTID_PARAM);
        String timeStamp= request.getParameter(DEFAULT_TIMESTAMP_PARAM);
        String digest= request.getParameter(DEFAUL_DIGEST_PARAM);
        Map<String, String[]> parameters = request.getParameterMap();
        String host = request.getRemoteHost();
        return new HmacToken(clientId, timeStamp, digest, host,parameters);
    }

    protected boolean isHmacSubmission(ServletRequest request) {
        String clientId = request.getParameter(DEFAULT_CLIENTID_PARAM);
        String timeStamp = request.getParameter(DEFAULT_TIMESTAMP_PARAM);
        String digest = request.getParameter(DEFAUL_DIGEST_PARAM);
        return (request instanceof HttpServletRequest && StringUtils.isNotEmpty(clientId)
                && StringUtils.isNotEmpty(timeStamp) && StringUtils.isNotEmpty(digest));
    }
}
