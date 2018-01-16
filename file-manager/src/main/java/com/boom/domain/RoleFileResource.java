package com.boom.domain;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午8:25
 * @Description 角色文件资源文件资源类
 */
@TableName("file")
public class RoleFileResource extends SystemFileResource implements Serializable {
    private static final long serialVersionUID = 7860365655007751252L;

//    @TableField("role_id")
    private Integer roleId;

    public RoleFileResource() {
        super();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
