package com.tensquare.user.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * tb_admin
 * @author
 */
@Table(name="tb_admin")
@Entity
public class Admin implements Serializable {
    /**
     * ID
     */
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 登陆名称
     */
    private String loginname;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Admin{");
        sb.append("id='").append(id).append('\'');
        sb.append(", loginname='").append(loginname).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
