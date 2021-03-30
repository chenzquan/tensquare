package com.tensquare.user.controller;

import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tensquare.common.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        user = userService.login(user.getMobile(),user.getPassword());
        if(user == null){
            return new Result(true, StatusCode.LOGINERROR,"登录失败");
        }else{
            String token = jwtUtil.createJWT(user.getId(),user.getNickname(),"user");
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",user.getNickname());  //呢称
            map.put("avatar",user.getAvatar());  //头像
            return new Result(true, StatusCode.OK,"登录成功",map);
        }


    }

    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK,"发送成功");
    }

    /**
     * 用户注册
     * @param user
     */
    @RequestMapping(value="/register/{code}",method=RequestMethod.POST)
    public Result register(@RequestBody User user , @PathVariable String code){
        userService.add(user,code);
        return new Result(true,StatusCode.OK,"注册成功");
    }


    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id){
//        String authHeader = request.getHeader("Authorization");//获取头信息
//        if(authHeader==null){
//            throw new RuntimeException("权限不足");
//            //return new Result(false,StatusCode.ACCESSERROR,"权限不足");
//        }
//        if(!authHeader.startsWith("Bearer ")){
//            throw new RuntimeException("权限不足");
//            //return new Result(false,StatusCode.ACCESSERROR,"权限不足");
//        }
//        String token = authHeader.substring(7);//提取token
//        Claims claims = jwtUtil.parseJWT(token);
//        if(claims==null){
//            throw new RuntimeException("权限不足");
//            //return new Result(false,StatusCode.ACCESSERROR,"权限不足");
//        }
//        if(!"admin".equals(claims.get("roles"))){
//            throw new RuntimeException("权限不足");
//            //return new Result(false,StatusCode.ACCESSERROR,"权限不足");
//        }

        Claims claims=(Claims) request.getAttribute("admin_claims");
        if(claims==null){
            return new Result(true,StatusCode.ACCESSERROR,"无权访问");
        }
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
