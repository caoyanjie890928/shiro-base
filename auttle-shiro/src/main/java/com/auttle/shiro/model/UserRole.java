package com.auttle.shiro.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "user_role")
public class UserRole {
    @Column(name = "userId")
    private Integer userid;
    @Column(name = "roleId")
    private String roleid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}