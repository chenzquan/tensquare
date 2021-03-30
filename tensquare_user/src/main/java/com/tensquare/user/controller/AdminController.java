package com.tensquare.user.controller;

import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import com.tensquare.common.utils.JwtUtil;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;




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
            //生成token
            String token = jwtUtil.createJWT(admin.getId(),admin.getLoginname(),"admin");
            Map map=new HashMap();
            map.put("token",token);
            map.put("name",admin.getLoginname());//登陆名
            return new Result(true,StatusCode.OK,"登陆成功",map);
//            return new Result(true,StatusCode.OK,"登陆成功");
        }else{
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }


    @GetMapping(value = "/test")
    public Result test(){

        long startTime  = System.currentTimeMillis();
        List<Object[]> users = new ArrayList<>();
        for (int i=1; i<=100000; i++){
            users.add(new Object[]{i,"i","a","1"});

        }

        String sql = "insert into tb_admin (id,loginname,password,state) values (?,?,?,?)";
        jdbcTemplate.batchUpdate(sql,users);

        long engTime = System.currentTimeMillis();

        System.out.println("shijian: " + (engTime - startTime));
        return new Result(true, StatusCode.OK,"test");
    }

    @GetMapping(value = "/test2")
    public Result test2(){

        long startTime  = System.currentTimeMillis();
        String sql = "insert into tb_admin (id,loginname,password,state) values (?,?,?,?)";
      //  List<Object[]> users = new ArrayList<>();
        for (int i=1; i<=100000; i++){

     //       users.add(new Object[]{i,"i","a","1"});
            jdbcTemplate.update(sql,i,"i","a","1");
        }


      //  jdbcTemplate.batchUpdate(sql,users);

        long engTime = System.currentTimeMillis();

        System.out.println("shijian2: " + (engTime - startTime));
        return new Result(true, StatusCode.OK,"test");
    }


}
