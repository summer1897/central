package com.boom.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/22
 * @Time 14:25
 * @Description 一般shiro用户凭证信息可以用 {@link org.apache.shiro.authc.UsernamePasswordToken},
 * 这里自己实现 {@link AuthenticationToken}
 */
public class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = -5076248266828307293L;
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
