package com.tensquare.friend.pojo;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * tb_friend
 * @author
 */
@Entity
@Table(name = "tb_friend")
public class Friend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

    /**
     * 是否互相喜欢
     */
    private String islike;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TbFriend{");
        sb.append("userid='").append(userid).append('\'');
        sb.append(", friendid='").append(friendid).append('\'');
        sb.append(", islike='").append(islike).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
