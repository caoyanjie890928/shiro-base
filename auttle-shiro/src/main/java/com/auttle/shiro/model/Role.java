package com.auttle.shiro.model;

import javax.persistence.Column;
import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = -9188614500715370053L;
    private Integer id;

    @Column(name = "roleDesc")
    private String roledesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }
}