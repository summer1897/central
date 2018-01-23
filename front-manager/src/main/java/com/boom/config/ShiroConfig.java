package com.boom.config;

import com.boom.shiro.*;
import com.google.common.collect.Maps;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by summer on 2017/12/7.
 */
@Configuration
public class ShiroConfig {

    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 定义ShiroFilterFactoryBean处理资源拦截
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("ShiroConfig.shiroFilter method invoke");

        //1.初始化ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        /*Map<String, Filter> filterMap = Maps.newHashMap();
        filterMap.put("tokenFilter", new TokenFilter());
        shiroFilterFactoryBean.setFilters(filterMap);*/

        //2.为ShiroFilterFactoryBean设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //3.配置拦截路径
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //配置退出路径
        filterChainDefinitionMap.put("/logout","logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，
        // 一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/login.json","anon");
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        filterChainDefinitionMap.put("/**","authc");
//        filterChainDefinitionMap.put("/**","tokenFilter");
//        filterChainDefinitionMap.put("/auth/**","hmac");

        //4.设置登录页面,默认回去webapp下面寻找login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        //5.设置登录成功默认显示页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //6.设置为授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public RBACShiroRealm rBACShiroRealm() {
        return new RBACShiroRealm();
    }

    @Bean
    public HmacRealm getHmacRealm() {
        return new HmacRealm();
    }

    @Bean
    public AgileSubjectFactory getAgileSubjectFactory() {
        return new AgileSubjectFactory();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(rBACShiroRealm());
//        securityManager.setRealm(getHmacRealm());
        securityManager.setSubjectFactory(getAgileSubjectFactory());

        /**
         * 关闭shiro自带的session
         */
        /*DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);

        securityManager.setSubjectDAO(subjectDAO);*/

        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

}
