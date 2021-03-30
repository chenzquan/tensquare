package com.tensquare.user.interceptor;

import com.tensquare.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");

        //无论如何放行，具体能不能操作还是在具体的操作中去判断
        //拦截器只是负责把头请求中包含token的令牌进行一个解析验证

        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7); // The part after "Bearer "
            try{
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    if("admin".equals(claims.get("roles"))){//如果是管理员
                        request.setAttribute("admin_claims", claims);
                    }
                    if("user".equals(claims.get("roles"))){//如果是用户
                        request.setAttribute("user_claims", claims);
                    }
                }
            }catch (Exception e){
                throw new RuntimeException("令牌不正确！");
            }
        }

        return true;
    }

}
