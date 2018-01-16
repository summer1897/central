package com.boom.domain;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午10:14
 * @Description 角色权限关系实体对象
 */
public class RolePermission extends BaseDomain<RolePermission> implements Serializable {

    private static final long serialVersionUID = -8159790746838918L;

    private Integer roleId;
    private Integer permissonId;

    public RolePermission() {
    }

    public RolePermission(Integer roleId, Integer permissonId) {
        this.roleId = roleId;
        this.permissonId = permissonId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissonId() {
        return permissonId;
    }

    public void setPermissonId(Integer permissonId) {
        this.permissonId = permissonId;
    }
}
