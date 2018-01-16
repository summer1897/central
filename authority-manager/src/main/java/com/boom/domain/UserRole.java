package com.boom.domain;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午10:12
 * @Description 用户角色关系实体对象
 */
public class UserRole extends BaseDomain<UserRole> implements Serializable{

    private static final long serialVersionUID = -2450084104823370600L;
    private Integer userId;
    private Integer roleId;

    public UserRole() {
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
