package com.boom.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.boom.domain.Permission;
import com.boom.domain.Role;
import com.boom.manager.IUserManager;
import com.boom.service.IPermissionService;
import com.boom.service.IRolePermissionService;
import com.boom.service.IRoleService;
import com.boom.service.IUserRoleService;
import com.boom.service.dto.Node;
import com.boom.service.dto.SimpleRoleDto;
import com.boom.utils.TreeNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午8:01
 * @Description
 */
@Component
@Transactional
public class UserManagerImpl implements IUserManager {

    private static final Logger log = LoggerFactory.getLogger(UserManagerImpl.class);
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRolePermissionService rolePermissionService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public List<Node> queryTreeMenu(Long userId) {
        log.info("Manager layer=========>UserManagerImpl.queryTreeMenu()");

        List<Permission> permissions = this.queryPermissionOfUser(userId);
        TreeNode treeNode = new TreeNode(permissions);
        return treeNode.buildTree().getChildren();
    }

    @Override
    public List<String> queryUserPermission(Long userId) {
        log.info("Manager layer=========>UserManagerImpl.queryUserPermission()");

        List<String> permissionNames = Lists.newArrayList();
        List<Permission> permissions = this.queryPermissionOfUser(userId);
        if (ObjectUtils.isNotEmpty(permissions)) {
            permissionNames.addAll(PropertyUtils.extractPropertyFromDomain(permissions,"permission",String.class));
        }
        return permissionNames;
    }

    private List<Permission> queryPermissionOfUser(Long userId) {
        List<Permission> permissions = Lists.newArrayList();
        List<Long> permissionIds = Lists.newArrayList();

        List<Long> roleIds = this.queryRoleIdsOfUser(userId);

        if (ObjectUtils.isNotEmpty(roleIds)) {
            permissionIds = rolePermissionService.queryPermissionOfRoles(Sets.newHashSet(roleIds));
        }
        if (ObjectUtils.isNotEmpty(permissionIds)) {
            permissions.addAll(permissionService.selectBatchIds(permissionIds));
        }

        return permissions;
    }

    @Override
    public List<SimpleRoleDto> queryUserRoles(Long userId) {
        log.info("Manager layer=========>UserManagerImpl.queryUserRoles()");

        List<Role> roles = Lists.newArrayList();
        List<SimpleRoleDto> simpleRoles = Lists.newArrayList();
        List<Long> roleIds = this.queryRoleIdsOfUser(userId);

        if (ObjectUtils.isNotEmpty(roleIds)) {
            EntityWrapper condition = new EntityWrapper();
            condition.in("id",roleIds).and().eq("available",Role.AVAILABLE);
            roles.addAll(roleService.selectList(condition));
        }

        if (ObjectUtils.isNotEmpty(roles)) {
            simpleRoles.addAll(BeanCloneUtils.clone(roles,Role.class,SimpleRoleDto.class));
        }

        return simpleRoles;
    }

    @Override
    public List<String> queryUserRolesStr(Long userId) {
        log.info("Manager layer=========>UserManagerImpl.queryUserRoles()");

        List<String> roleNames = Lists.newArrayList();
        List<SimpleRoleDto> simpleRoles = this.queryUserRoles(userId);

        if (ObjectUtils.isNotEmpty(simpleRoles)) {
            roleNames.addAll(PropertyUtils.extractPropertyFromDomain(simpleRoles,"name",String.class));
        }

        return roleNames;
    }

    private List<Long> queryRoleIdsOfUser(Long userId) {
        List<Long> roleIds = userRoleService.queryByUserId(userId);
        return roleIds;
    }

    @Override
    public boolean addRoles(Long userId, Set<Long> roleIds) {
        log.info("Manager layer=========>UserManagerImpl.addRoles()");

        return userRoleService.add(userId, roleIds);
    }

    @Override
    public boolean deleteRoles(Long userId, Set<Long> roleIds) {
        log.info("Manager layer=========>UserManagerImpl.deleteRoles()");

        return userRoleService.delete(userId, roleIds);
    }
}
