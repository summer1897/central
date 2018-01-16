package com.boom.service;



import com.baomidou.mybatisplus.service.IService;
import com.boom.domain.RolePermission;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午4:57
 * @Description 角色权限管理信息服务层操作接口定义
 */
public interface IRolePermissionService extends IService<RolePermission> {

    /**
     * 查询角色对应的所有权限ID
     * @param roleId
     * @return {@link List<Integer>}
     */
    List<Integer> queryPermissionOfRole(Integer roleId);

    /**
     * 查询多个角色对应的所有权限ID
     * @param roleIds
     * @return {@link List<Integer>}
     */
    List<Integer> queryPermissionOfRoles(Set<Integer> roleIds);

    /**
     * 为角色添加一个权限管理
     * @param roleId
     * @param permissionId
     * @return {@link boolean}
     */
    boolean correlation(Integer roleId, Integer permissionId);

    /**
     * 为角色添加一组权限
     * @param roleId
     * @param permissionIds
     * @return {@link boolean}
     */
    boolean correlation(Integer roleId, Set<Integer> permissionIds);

    /**
     * 删除角色权限
     * @param roleId
     * @param permissionId
     * @return {@link boolean}
     */
    boolean uncorrelation(Integer roleId, Integer permissionId);

    /**
     * 删除角色多组权限
     * @param roleId
     * @param permissionIds
     * @return {@link boolean}
     */
    boolean uncorrelation(Integer roleId, Set<Integer> permissionIds);

    /**
     * 删除多个角色及其对应的权限
     * @param roleIds
     * @param permissionIds
     * @return {@link boolean}
     */
    boolean uncorrelation(Set<Integer> roleIds, Set<Integer> permissionIds);

    /**
     * 删除角色的所有权限
     * @param roleId
     * @return {@link Integer}
     */
    boolean uncorrelationAllPermissionOfRole(Integer roleId);

    /**
     * 删除多个角色的所有权限
     * @param roleIds
     * @return {@link Integer}
     */
    boolean uncorrelationAllPermissionOfRoles(Set<Integer> roleIds);

}
