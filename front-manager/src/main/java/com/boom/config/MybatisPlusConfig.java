package com.boom.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Intellij IDEA
 *
 * @Projcet repository
 * @Author summer
 * @Date 2018/1/15
 * @Time 14:43
 * @Description
 */
@Configuration
@MapperScan("com.boom.summer.dao*")
public class MybatisPlusConfig {

}
