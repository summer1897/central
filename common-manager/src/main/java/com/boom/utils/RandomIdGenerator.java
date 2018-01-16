package com.boom.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * Created by summer on 2017/12/11.
 * 随机Id生成器,既可以生成存数字的，也可以生成包含字母的
 */
public class RandomIdGenerator {

    private static final Logger log = LoggerFactory.getLogger(RandomIdGenerator.class);

    /**存数字随机数生成所采用的算法*/
    private static final String RANDOM_NUM_GENERATOR_ALGORITHM_NAME = "SHA1PRNG";

    /**
     * 使用UUID工具类生成动态字符串,并去除掉字符串中的'-'符号
     * @return Serializable
     */
    public static final Serializable geneateUUID() {
        log.info("生成UUDI随机数===>RandomIdGenerator.geneateUUID()");
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 使用SecureRandom工具类生成存数字的随机数
     * @return Serializable
     */
    public static final Serializable generateRandomId() {
        Random random = null;
        try {
            random = SecureRandom.getInstance(RANDOM_NUM_GENERATOR_ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException e) {
            log.error("该随机数生成算法不存在");
            random = new SecureRandom();
        }
        log.info("生成存数字随机数===>RandomIdGenerator.generateRandomId()");

        //如果为负数，去掉符号，再返回字符串
        return Long.toString(random.nextLong()).replaceAll("-","");
    }

    public static void main(String[] args) {
        System.out.println("uuid: " + geneateUUID());
        System.out.println("random id: " + generateRandomId());
    }
}
