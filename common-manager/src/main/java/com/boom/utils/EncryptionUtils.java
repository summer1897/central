package com.boom.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by summer on 2017/12/11.
 * 加密工具
 */
public class EncryptionUtils {

    private static final String DEFAULT_ALGORITHM = "SHA-1";

    public static String encrypt(String text,String salt) {
        SimpleHash simpleHash = new SimpleHash(DEFAULT_ALGORITHM,text,salt);
        return simpleHash.toBase64();
    }

    public static String encrypt(String text,String salt,String algorithm) {
        SimpleHash simpleHash = new SimpleHash(algorithm,text,salt);
        return simpleHash.toBase64();
    }

}
