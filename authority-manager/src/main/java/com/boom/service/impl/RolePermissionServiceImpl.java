package com.boom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.dao.RolePermissionMapper;
import com.boom.domain.RolePermission;
import com.boom.service.IRolePermissionService;
import com.google.common.collect.Lists;
import com.summer.base.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午5:21
 * @Description
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper,RolePermission> implements IRolePermissionService {

    private static final Logger log = LoggerFactory.getLogger(RolePermissionServiceImpl.class);
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Integer> queryPermissionOfRole(Integer roleId) {
        log.info("Service layer============>RolePermissionServiceImpl.queryPermissionId()");

        List<Integer> permissionIds = Lists.newArrayList();
        if (ObjectUtils.isNotNull(roleId)) {
            permissionIds = rolePermissionMapper.queryPermissionOfRole(roleId);
        }
        return permissionIds;
    }

    @Override
    public List<Integer> queryPermissionOfRoles(Set<Integer> roleIds) {
        log.info("Service layer============>RolePermissionServiceImpl.queryPermissionId()");

        List<Integer> permissionIds = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(roleIds)) {
            permissionIds = rolePermissionMapper.queryPermissionOfRoles(roleIds);
        }
        return permissionIds;
    }

    @Override
    public boolean correlation(Integer roleId, Integer permissionId) {
        log.info("Service layer============>RolePermissionServiceImpl.correlation()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(roleId)) {
            isSuccess = retBool(rolePermissionMapper.correlation(roleId,permissionId));
        }
        return isSuccess;
    }

    @Override
    public boolean correlation(Integer roleId, Set<Integer> permissionIds) {
        log.info("Service layer============>RolePermissionServiceImpl.correlation()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(roleId) && ObjectUtils.isNotEmpty(permissionIds)) {
            isSuccess = retBool(rolePermissionMapper.correlations(roleId,permissionIds));
        }
        return isSuccess;
    }

    @Override
    public boolean uncorrelation(Integer roleId, Integer permissionId) {
        log.info("Service layer============>RolePermissionServiceImpl.uncorrelation()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(roleId)) {
            isSuccess = retBool(rolePermissionMapper.uncorrelation(roleId,permissionId));
        }
        return isSuccess;
    }

    @Override
    public boolean uncorrelation(Integer roleId, Set<Integer> permissionIds) {
        log.info("Service layer============>RolePermissionServiceImpl.uncorrelation()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(roleId) && ObjectUtils.isNotEmpty(permissionIds)) {
            isSuccess = retBool(rolePermissionMapper.uncorrelations(roleId,permissionIds));
        }
        return isSuccess;
    }

    @Override
    public boolean uncorrelation(Set<Integer> roleIds, Set<Integer> permissionIds) {
        log.info("Service layer============>RolePermissionServiceImpl.uncorrelation()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotEmpty(roleIds) && ObjectUtils.isNotEmpty(permissionIds)) {
            isSuccess = retBool(rolePermissionMapper.uncorrelationRoles(roleIds,permissionIds));
        }
        return isSuccess;
    }

    @Override
    public boolean uncorrelationAllPermissionOfRole(Integer roleId) {
        log.info("Service layer============>RolePermissionServiceImpl.uncorrelationAllPermissionOfRole()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(roleId)) {
            isSuccess = retBool(rolePermissionMapper.uncorrelationAllPermissionOfRole(roleId));
        }
        return isSuccess;
    }

    @Override
    public boolean uncorrelationAllPermissionOfRoles(Set<Integer> roleIds) {
        log.info("Service layer============>RolePermissionServiceImpl.uncorrelationAllPermissionOfRoles()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotEmpty(roleIds)) {
            isSuccess = retBool(rolePermissionMapper.uncorrelationAllPermissionOfRoles(roleIds));
        }
        return isSuccess;
    }
}
