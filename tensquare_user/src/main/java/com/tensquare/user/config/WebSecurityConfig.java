package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //authorizeRequests 所有security 全注解配置实现的开端，表示开始说明需要的权限
    // 需要的权限分两部分，第一部分是拦截的路径，第二部分访问该路径需要的权限
    //antMatchers 表示拦截什么路径，permitAll任何权限都可以访问
    //anyRequest 表示任务的请求，authenticated认证后才能访问
    //.and().csrf().disable() 固定写法，表示使 csrf 拦截失败
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
