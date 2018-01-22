package com.boom;

import com.alibaba.fastjson.JSON;
import com.boom.test.JwtAuthenticationFilter;
import com.boom.test.JwtUtil;
import com.boom.vo.ResultVo;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/22 下午10:43
 * @Description
 */
@RestController
@SpringBootApplication
public class App1 {

    private static final Logger log = LoggerFactory.getLogger(App1.class);

    @GetMapping("/api/sayHello.json")
    public ResultVo sayHello() {
        return ResultVo.success("Hello,this is a protected api");
    }

    @PostMapping("/login")
    public void login(@RequestBody AccountCredentials credentials,HttpServletResponse response) throws IOException {
        log.warn("credentials:{}", JSON.toJSONString(credentials,true));
        //here we just have one hardcoded username=admin and password=admin
        if(validCredentials(credentials)) {
            String jwt = JwtUtil.sign(credentials.username);
            response.addHeader(JwtUtil.HEADER_STRING, JwtUtil.TOKEN_PREFIX + " " + jwt);
//            return ResultVo.success(Maps.newHashMap().put(JwtUtil.HEADER_STRING,JwtUtil.TOKEN_PREFIX + " " + jwt));
        }else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong credentials");
//            return ResultVo.fail(HttpServletResponse.SC_UNAUTHORIZED + ":Wrong credentials");
        }
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(
                "/api/**");
        registrationBean.setFilter(filter);
        return registrationBean;
    }

    private boolean validCredentials(AccountCredentials credentials) {
        return "admin".equals(credentials.username)
                && "admin".equals(credentials.password);
    }


    public static class AccountCredentials {
        public String username;
        public String password;
    }
    public static void main(String[] args) {
        SpringApplication.run(App1.class, args);
    }


}
