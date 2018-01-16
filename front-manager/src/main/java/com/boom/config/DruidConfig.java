package com.boom.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午11:58
 * @Description Druid数据连接池配置
 */
@Configuration
public class DruidConfig {

    private static final Logger log = LoggerFactory.getLogger(DruidConfig.class);

    public static final String LOGIN_USERNAME = "loginUsername";
    public static final String USERNAME = "admin";
    public static final String LOGIN_PASSWORD = "loginPassword";
    public static final String PASSWORD = "123456";
    public static final String URL_MAPPING = "/druid/*";

    @Bean
    public ServletRegistrationBean druidServlet() {
        log.info("配置Druid后台管理系统参数====>DruidConfig.druidServlet()");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),URL_MAPPING);
        //设置druid数据连接池管理系统登录用户名与密码
        servletRegistrationBean.addInitParameter(LOGIN_USERNAME,USERNAME);
        servletRegistrationBean.addInitParameter(LOGIN_PASSWORD,PASSWORD);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        log.info("注册过滤器===>DruidConfig.filterRegistrationBean()");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
