package com.boom.shiro;

import com.boom.enums.AccountSatus;
import com.boom.manager.IAccountCredentialsManager;
import com.boom.service.dto.AccountCredentialsDto;
import com.boom.utils.JWTUtils;
import com.boom.vo.Principal;
import com.google.common.collect.Lists;
import com.summer.base.utils.ObjectUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:10
 * @Description 基于HMAC（ 散列消息认证码）的控制域
 */
public class HmacRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(HmacRealm.class);
    @Autowired
    private IAccountCredentialsManager accountCredentialsManager;

    @Override
    public Class<?> getAuthenticationTokenClass() {
        //此Realm只支持HmacToken
        return HmacToken.class;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("权限配置===>HmacRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Principal principal = (Principal) principals.getPrimaryPrincipal();
        Long userId = principal.getId();
        List<String> roles = accountCredentialsManager.queryRoles(userId);
        List<String> permissions = accountCredentialsManager.queryPermissions(userId);
        if (ObjectUtils.isNotEmpty(roles)) {
            simpleAuthorizationInfo.addRoles(roles);
        }
        if (ObjectUtils.isNotEmpty(permissions)) {
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        HmacToken hmacToken = (HmacToken) token;

        //用户验证
        AccountCredentialsDto accountCredentials = accountCredentialsManager.queryByUserName(hmacToken.getClientId());
        if (ObjectUtils.isNull(accountCredentials)) {
            throw new UnknownAccountException("该账户不存在");
        } else {
            if (accountCredentials.getLocked() == AccountSatus.UN_ACTIVATION_STATUS.getStatus()) {
                throw new UnknownAccountException("该账户未激活");
            } else if (accountCredentials.getLocked() == AccountSatus.FORBIDDEN_STATUS.getStatus()) {
                throw new UnknownAccountException("该账户已被禁用");
            }
        }


        List<String> keys = Lists.newArrayList();
        Map<String, String[]> parameters = hmacToken.getParameters();
        for (String key : parameters.keySet()) {
            if (!HmacToken.DIGEST.equalsIgnoreCase(key)) {
                keys.add(key);
            }
        }

        Collections.sort(keys);
        StringBuilder baseString = new StringBuilder();
        keys.forEach(key -> {
            baseString.append(parameters.get(key)[0]);
        });

        //认证端摘要生成
        String serverDigest = JWTUtils.hmacDigest(baseString.toString());
        //客户端摘要与服务端摘要比对
        if (!hmacToken.getDigest().equals(serverDigest)) {
            throw new AuthenticationException("数字摘要验证失败");
        }

        Long visitedTimeStamp = Long.valueOf(hmacToken.getTimeStamp());
        Long nowTimeStamp = System.currentTimeMillis();
        Long duration = nowTimeStamp - visitedTimeStamp;
        if (duration > HmacToken.EXPIRATION_TIME) {
            throw new AuthenticationException("数字摘要时间失效");
        }
        return new SimpleAuthenticationInfo(hmacToken.getClientId(),Boolean.TRUE,getName());
    }
}
