package com.boom.domain;


import java.io.Serializable;

/**
 * Created by yangyang on 2017/12/7.
 */
public class Role extends BaseDomain<Role> implements Serializable {
    private static final long serialVersionUID = 565651037360395463L;

    /**角色名*/
    private String name;
    /**角色描述*/
    private String description;
    /**标识角色是否可用，0：不可用，1：可用*/
    private Byte available = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

}
