package com.boom.shiro;

import com.boom.domain.AccountCredentials;
import com.boom.domain.User;
import com.boom.manager.IAccountCredentialsManager;
import com.boom.manager.IUserManager;
import com.boom.service.IAccountCredentialsService;
import com.boom.service.IUserService;
import com.boom.vo.Principal;
import com.summer.base.utils.ObjectUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by summer on 2017/12/7.
 */
public class RBACShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(RBACShiroRealm.class);

    @Autowired
    private IAccountCredentialsService accountCredentialsService;
    @Autowired
    private IAccountCredentialsManager accountCredentialsManager;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置===>RBACShiroRealm.doGetAuthorizationInfo()");
        Principal principal = (Principal) principals.getPrimaryPrincipal();
        Long userId = principal.getId();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        List<String> roles = accountCredentialsManager.queryRoles(userId);
        List<String> permissions = accountCredentialsManager.queryPermissions(userId);

        if (ObjectUtils.isNotEmpty(roles)) {
            authorizationInfo.addRoles(roles);
        }
        if (ObjectUtils.isNotEmpty(permissions)) {
            authorizationInfo.addStringPermissions(permissions);
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("RBACShiroRealm.doGetAuthenticationInfo(),用户身份认证");
        String userName = (String) authenticationToken.getPrincipal();

        //从数据库中，根据当前用户名查找对应的用户
        AccountCredentials account = accountCredentialsService.queryByUserName(userName);

        if (ObjectUtils.isNull(account)) {
            log.info("当前用户不存在");
            throw new AuthenticationException("用户名或密码错误!");
        } else {
            Byte locked = account.getLocked();
            if (Principal.STATUS_NO_ACTIVATION == locked) {
                throw new AuthenticationException("改用户还为激活!");
            } else if (Principal.STATUS_NO_LOCKED == locked) {
                throw new AuthenticationException("用户已被禁用!");
            } else if (Principal.STATUS_NO_NORMAL == locked){
                ByteSource pwdSalt = ByteSource.Util.bytes(account.getCredentialSalt());
                return new SimpleAuthenticationInfo(userName,
                        account.getPassword(),
                        pwdSalt,
                        getName());
            } else {
                throw new AuthenticationException("用户无法登陆!");
            }
        }
    }

    @Override
    public String getName() {
        return "RBACShiroRealm";
    }
}
