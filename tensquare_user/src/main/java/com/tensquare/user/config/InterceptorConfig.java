package com.tensquare.user.config;

import com.tensquare.user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器要声明拦截器对象和要拦截的请求
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");

      //  jdbcTemplate.update("","");
   //     jdbcTemplate.batchUpdate("",new ArrayList<>());
    }
}
