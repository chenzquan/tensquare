package com.tensquare.user.service;

import com.tensquare.common.utils.IdWorker;
import com.tensquare.user.UserDao;
import com.tensquare.user.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserDao userDao;

    public void sendSms(String mobile) {
        //生成6位数随机数
        String code = RandomStringUtils.randomNumeric(6);
        System.out.println(mobile+"收到验证码是："+ code);

        //向缓存中放一份
     //2.将验证码放入redis
        redisTemplate.opsForValue().set("smscode_"+mobile, code+"" ,5, TimeUnit.MINUTES );//五分钟过期
        //redisTemplate.opsForValue().set("checkcode_" + mobile,checkcode,6, TimeUnit.MINUTES);


        //3.将验证码和手机号发动到rabbitMQ中
        Map<String,String> map = new HashMap();
        map.put("mobile",mobile);
        map.put("code",code+"");
        rabbitTemplate.convertAndSend("sms",map);
    }


    /**
     * 增加
     * @param user 用户
     * @param code 用户填写的验证码
     */
    public void add(User user, String code) {
//判断验证码是否正确
        String syscode = (String)redisTemplate.opsForValue().get("smscode_" + user.getMobile());
//提取系统正确的验证码
        if(syscode==null){
            throw new RuntimeException("请点击获取短信验证码");
        }
        if(!syscode.equals(code)){
            throw new RuntimeException("验证码输入不正确");
        }
        user.setId( idWorker.nextId()+"");
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        userDao.save(user);
    }


}
