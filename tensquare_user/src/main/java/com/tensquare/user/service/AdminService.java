package com.tensquare.user.service;

import com.tensquare.common.utils.IdWorker;
import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private AdminDao adminDao;


    public void add(Admin admin) {
        admin.setId(idWorker.nextId()+""); //主键值
        // 密码加密
        String newpassword = encoder.encode(admin.getPassword());//加密后的密码
        admin.setPassword(newpassword);
        adminDao.save(admin);
    }


    /**
     * 根据登陆名和密码查询
     * @param loginname
     * @param password
     * @return
     */
    public Admin findByLoginnameAndPassword(String loginname, String password){
        Admin admin = adminDao.findByLoginname(loginname);
        if( admin!=null && encoder.matches(password,admin.getPassword())){

            return admin;
        }else{
            return null;
        }
    }

}
