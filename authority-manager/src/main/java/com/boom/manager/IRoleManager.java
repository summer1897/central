package com.boom.manager;

import com.boom.controller.vo.RolePermissionVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午4:54
 * @Description
 */
public interface IRoleManager {

    /**
     * 为角色添加权限
     * @param rolePermissionVo
     * @return {@link boolean}
     */
    boolean authorize(RolePermissionVo rolePermissionVo);

    /**
     *
     * @param id
     * @return {@link boolean}
     */
    boolean delete(Long id);

    /**
     *
     * @param ids
     * @return {@link boolean}
     */
    boolean deleteBatch(Set<Long> ids);

    /**
     * 获取角色的所有权限Id
     * @param roleId
     * @return {@link List<Long>}
     */
    List<Long> queryAllRolePermissionId(Long roleId);

}
