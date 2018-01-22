package com.boom.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.summer.base.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/22 下午9:57
 * @Description
 */
public class JwtUtil {

    private static final long EXPIRATION_TIME = 3600_000;

    private static final String SECRET = "ThisIsASecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    private static final String USER_NAME = "username";

    public static String sign(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                .withClaim("username",username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String verify(String token) {
        if (StringUtils.isNotEmpty(token)) {
            return getUsername(token);
        }
        throw new TokenValidationException("Missing token");
    }

    public static String getUsername(String token) {
        try {
            DecodedJWT decoded = JWT.decode(token);
            return decoded.getClaim(USER_NAME).asString();
        } catch (Exception e) {
            return null;
        }
    }

}
