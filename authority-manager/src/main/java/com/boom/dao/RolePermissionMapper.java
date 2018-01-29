package com.boom.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午5:18
 * @Description DAO层操作
 */
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 查询角色对应的所有权限ID
     * @param roleId
     * @return {@link List<Long>}
     */
    List<Long> queryPermissionOfRole(@Param("roleId") Long roleId);

    /**
     * 查询多个角色对应的所有权限ID
     * @param roleIds
     * @return {@link List<Long>}
     */
    List<Long> queryPermissionOfRoles(@Param("roleIds") Set<Long> roleIds);

    /**
     * 为角色添加一个权限管理
     * @param roleId
     * @param permissionId
     * @return {@link Integer}
     */
    Integer correlation(@Param("roleId") Long roleId,
                        @Param("permissionId") Long permissionId);

    /**
     * 为角色添加一组权限
     * @param roleId
     * @param permissionIds
     * @return {@link Integer}
     */
    Integer correlations(@Param("roleId") Long roleId,
                         @Param("permissionIds") Set<Long> permissionIds);

    /**
     * 删除角色权限
     * @param roleId
     * @param permissionId
     * @return {@link Integer}
     */
    Integer uncorrelation(@Param("roleId") Long roleId,
                          @Param("permissionId") Long permissionId);

    /**
     * 删除角色多组权限
     * @param roleId
     * @param permissionIds
     * @return {@link Integer}
     */
    Integer uncorrelations(@Param("roleId") Long roleId,
                           @Param("permissionIds") Set<Long> permissionIds);

    /**
     * 删除多个角色及其对应的权限
     * @param roleIds
     * @param permissionIds
     * @return {@link Integer}
     */
    Integer uncorrelationRoles(@Param("roleIds") Set<Long> roleIds,
                               @Param("permissionIds") Set<Long> permissionIds);

    /**
     * 删除角色的所有权限
     * @param roleId
     * @return {@link Integer}
     */
    Integer uncorrelationAllPermissionOfRole(@Param("roleId") Long roleId);

    /**
     * 删除多个角色的所有权限
     * @param roleIds
     * @return {@link Integer}
     */
    Integer uncorrelationAllPermissionOfRoles(@Param("roleIds") Set<Long> roleIds);

}
