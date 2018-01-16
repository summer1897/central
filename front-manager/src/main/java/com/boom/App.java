package com.boom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Intellij IDEA
 *
 * @Author susmmer
 * @Date 2017/12/8 上午12:10
 * @Description main startup class
 */
@SpringBootApplication
@MapperScan("com.boom.dao*")
@ComponentScan("com.boom")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

}
