package com.tensquare.user.controller;

import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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

}
