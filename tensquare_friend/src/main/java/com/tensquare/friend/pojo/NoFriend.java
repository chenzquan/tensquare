package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_nofriend")
public class NoFriend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NoFriend{");
        sb.append("userid='").append(userid).append('\'');
        sb.append(", friendid='").append(friendid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
