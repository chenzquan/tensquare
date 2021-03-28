package com.tensquare.user.controller;

import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import com.tensquare.common.utils.JwtUtil;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;




    /**
     * 用户注册
     * @param admin
     */
    @PutMapping
    public Result register(@RequestBody Admin admin){
        adminService.add(admin);
        return new Result(true, StatusCode.OK,"注册成功");
    }

    /**
     * 用户登陆
     * @param
     * @param
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> loginMap){

        //先根据用户名查询对象
        // 然后拿数据库中的密码和用户输入的密码匹配是否相同

        Admin admin = adminService.findByLoginnameAndPassword(loginMap.get("loginname"),loginMap.get("password"));
        if(admin != null){
            return new Result(true,StatusCode.OK,"登陆成功");
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }


}
