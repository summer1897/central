package com.boom.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/22
 * @Time 11:47
 * @Description JWT操作工具类
 */
public class JWTUtils {

    private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * Token过期时间,默认为半天时间过期
     */
    public static final long EXPIRATION_TIME = 12*60*60*1000;

    public static final String USER_NAME = "username";

    private static final String SECRET = "ThisIsASecret";

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param username 用户名
     * @param password 密码
     * @return boolean
     */
    public static boolean verify(String token,String username,String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                                      .withClaim("username",username)
                                      .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            //验证失败，直接返回false
            return false;
        }
    }

    /**
     * 获取Token中用户名信息，无需返回password信息
     * @param token
     * @return {@link String}
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT decoded = JWT.decode(token);
            return decoded.getClaim(USER_NAME).asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成签名信息，即token信息
     * @param username
     * @param password
     * @return token加密信息
     */
    public static String sign(String username,String password) {
        try {
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create()
                      .withClaim(USER_NAME,username)
                      .withExpiresAt(expireDate)
                      .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    public static String hmacDigest(String digest) {
        String secretDigest = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(digest);
            secretDigest = JWT.create().sign(algorithm);
        } catch (UnsupportedEncodingException e) {
        }
        return secretDigest;
    }

    public static String applyToken(String id,String subject,String issuer,Long period,
                                    String roles,String permissions) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create();
            long now = System.currentTimeMillis();

            if (StringUtils.isNotEmpty(id)) {
                builder.withJWTId(id);
            }
            if (StringUtils.isNotEmpty(subject)) {
                builder.withSubject(subject);
            }
            if (StringUtils.isNotEmpty(issuer)) {
                builder.withIssuer(issuer);
            }
            if (ObjectUtils.isNotNull(period)) {
                Date expiration = new Date(now + period);
                builder.withExpiresAt(expiration);
            } else {
                Date expiration = new Date(now + EXPIRATION_TIME);
                builder.withExpiresAt(expiration);
            }
            if (StringUtils.isNotEmpty(roles)) {
                builder.withClaim("roles",roles);
            }
            if (StringUtils.isNotEmpty(permissions)) {
                builder.withClaim("perms",permissions);
            }
            token = builder.sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return token;
    }

}
