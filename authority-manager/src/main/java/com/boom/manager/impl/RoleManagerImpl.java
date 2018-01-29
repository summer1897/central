package com.boom.manager.impl;

import com.boom.manager.IRoleManager;
import com.boom.service.IRolePermissionService;
import com.boom.service.IRoleService;
import com.summer.base.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午4:55
 * @Description
 */
@Transactional
@Component
public class RoleManagerImpl implements IRoleManager {

    private static final Logger log = LoggerFactory.getLogger(RoleManagerImpl.class);

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRolePermissionService rolePermissionService;


    @Override
    public boolean authorize(Long roleId, Set<Long> permissionIds) {
        log.info("Manager layer=============>RoleManagerImpl.authorize()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(roleId) && ObjectUtils.isNotEmpty(permissionIds)) {
            isSuccess = rolePermissionService.correlation(roleId,permissionIds);

        }
        return isSuccess;
    }

    @Override
    public boolean delete(Long id) {
        log.info("Manager layer=============>RoleManagerImpl.delete()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(id)) {
            isSuccess = roleService.deleteById(id) ||
                    rolePermissionService.uncorrelationAllPermissionOfRole(id);

        }
        return isSuccess;
    }

    @Override
    public boolean deleteBatch(Set<Long> ids) {
        log.info("Manager layer=============>RoleManagerImpl.deleteBatch()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotEmpty(ids)) {
            //可能会存在有角色信息，但是没有角色权限关联信息的情况，所有
            //两个删除操作有可能只进行一种操作
            isSuccess = roleService.deleteBatchIds(ids) ||
                    rolePermissionService.uncorrelationAllPermissionOfRoles(ids);

        }
        return isSuccess;
    }
}
