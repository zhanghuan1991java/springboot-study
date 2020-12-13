package com.didispace.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //创建   ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        /**
         * Shiro 内置过滤器：
         *      anon :无需认证
         *      authc :必须认证才能访问
         *      user : 使用rememberMe的功能，可以直接访问
         *      perms : 必须得到权限才能访问
         *      role : 必须得到角色才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/main","authc");

        factoryBean.setFilterChainDefinitionMap(filterMap);

        factoryBean.setLoginUrl("/login");

        return factoryBean;
    }

    //创建   DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }




}
