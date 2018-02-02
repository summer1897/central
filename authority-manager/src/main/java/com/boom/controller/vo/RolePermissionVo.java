package com.boom.controller.vo;

import com.boom.serializer.StringToLongSet;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/2/1
 * @Time 16:03
 * @Description
 */
public class RolePermissionVo implements Serializable {
    private static final long serialVersionUID = 6763674877819859322L;

    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 新添加的权限Id
     */
    private Set<Long> addingPermissionIds;
    /**
     * 待删除的权限Id
     */
    private Set<Long> deletingPermissionIds;

    public RolePermissionVo() {
    }

    public RolePermissionVo(Long roleId, Set<Long> addingPermissionIds, Set<Long> deletingPermissionIds) {
        this.roleId = roleId;
        this.addingPermissionIds = addingPermissionIds;
        this.deletingPermissionIds = deletingPermissionIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<Long> getAddingPermissionIds() {
        return addingPermissionIds;
    }

    @JsonDeserialize(using = StringToLongSet.class)
    public void setAddingPermissionIds(Set<Long> addingPermissionIds) {
        this.addingPermissionIds = addingPermissionIds;
    }

    public Set<Long> getDeletingPermissionIds() {
        return deletingPermissionIds;
    }

    @JsonDeserialize(using = StringToLongSet.class)
    public void setDeletingPermissionIds(Set<Long> deletingPermissionIds) {
        this.deletingPermissionIds = deletingPermissionIds;
    }
}
