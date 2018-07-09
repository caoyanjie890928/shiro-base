package com.auttle.shiro.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "role_resources")
public class RoleResources {

    @Column(name = "roleId")
    private Integer roleid;

    @Column(name = "resourcesId")
    private Integer resourcesid;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getResourcesid() {
        return resourcesid;
    }

    public void setResourcesid(Integer resourcesid) {
        this.resourcesid = resourcesid;
    }
}