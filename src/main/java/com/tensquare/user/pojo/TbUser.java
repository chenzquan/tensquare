package com.tensquare.user.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * tb_user
 * @author 
 */
@Table(name="tb_user")
@Data
public class TbUser implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月日
     */
    private Date birthday;

    /**
     * 头像
     */
    private String avatar;

    /**
     * E-Mail
     */
    private String email;

    /**
     * 注册日期
     */
    private Date regdate;

    /**
     * 修改日期
     */
    private Date updatedate;

    /**
     * 最后登陆日期
     */
    private Date lastdate;

    /**
     * 在线时长（分钟）
     */
    private Long online;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 个性
     */
    private String personality;

    /**
     * 粉丝数
     */
    private Integer fanscount;

    /**
     * 关注数
     */
    private Integer followcount;

    private static final long serialVersionUID = 1L;
}