package com.boom.shiro;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:04
 * @Description HMAC令牌
 */
public class HmacToken implements AuthenticationToken {
    private static final long serialVersionUID = -7271555013339351391L;

    public static final String DIGEST = "digest";
    /**
     * 数字摘要有效时间默认为1小时
     */
    public static final long EXPIRATION_TIME = 3600_000;

    /**
     * 客户标识，可以是用户名，App ID等
     */
    private String clientId;
    /**
     * 消息摘要
     */
    private String digest;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 访问参数
     */
    private Map<String,String[]> parameters;
    /**
     * 客户IP
     */
    private String ip;

    public HmacToken(String clientId, String digest, String timeStamp, String ip,Map<String, String[]> parameters) {
        this.clientId = clientId;
        this.digest = digest;
        this.timeStamp = timeStamp;
        this.parameters = parameters;
        this.ip = ip;
    }

    @Override
    public Object getPrincipal() {
        return this.clientId;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
