package com.boom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.dao.PermissionMapper;
import com.boom.domain.Permission;
import com.boom.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
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
 * @Date 2017/12/14 下午11:15
 * @Description 权限服务层操作接口实现类
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission>
                                    implements IPermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Qualifier("permissionMapper")
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> queryAll() {
        log.info("Service layer:=============>PermissionServiceImpl.queryAll()");
        return permissionMapper.queryAll();
    }

    @Override
    public List<Permission> queryAllAndPagination(Integer pageNum, Integer pageSize) {
        log.info("Service layer:=============>PermissionServiceImpl.queryAllAndPagination()");
        PageHelper.startPage(pageNum,pageSize);
        return permissionMapper.queryAll();
    }


    @Override
    public List<Permission> queryByIdsAndPagination(Set<Long> permissionIds, Integer pageNum, Integer pageSize) {
        log.info("Service layer:=============>PermissionServiceImpl.queryByIdsAndPagination()");

        List<Permission> permissions = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(permissionIds)) {
            PageHelper.startPage(pageNum,pageSize);
            permissions = permissionMapper.selectBatchIds(permissionIds);
        }

        return permissions;
    }

    @Override
    public List<Permission> queryChildren(Long permissionId) {
        log.info("Service layer:=============>PermissionServiceImpl.queryChildren()");

        List<Permission> permissions = Lists.newArrayList();
        if (ObjectUtils.isNotNull(permissionId)) {
            permissions = permissionMapper.queryChildren(permissionId);
        }
        return permissions;
    }

    @Override
    public List<Permission> queryChildren(Long permissionId, Integer pageNum, Integer pageSize) {
        log.info("Service layer:=============>PermissionServiceImpl.queryChildren()");

        List<Permission> permissions = Lists.newArrayList();
        if (ObjectUtils.isNotNull(permissionId)) {
            PageHelper.startPage(pageNum,pageSize);
            permissions = permissionMapper.queryChildren(permissionId);
        }
        return permissions;
    }

    @Override
    public List<Permission> queryAllChildren(Set<Long> permissionIds) {
        log.info("Service layer:=============>PermissionServiceImpl.queryAllChildren()");

        List<Permission> permissions = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(permissionIds)) {
            permissions = permissionMapper.queryAllChildren(permissionIds);
        }
        return permissions;
    }

    @Override
    public List<Permission> queryAllChildren(Set<Long> permissionIds, Integer pageNum, Integer pageSize) {
        log.info("Service layer:=============>PermissionServiceImpl.queryAllChildren()");

        List<Permission> permissions = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(permissionIds)) {
            PageHelper.startPage(pageNum,pageSize);
            permissions = permissionMapper.queryAllChildren(permissionIds);
        }
        return permissions;
    }

    @Override
    public List<Permission> queryLikeName(String name) {
        log.info("Service layer:=============>PermissionServiceImpl.queryLikeName()");

        List<Permission> permissions = Lists.newArrayList();
        if (StringUtils.isNotEmpty(name)) {
            permissions = permissionMapper.queryLikeName(name);
        }
        return permissions;
    }

    @Override
    public List<Permission> queryLikeName(String name, Integer pageNum, Integer pageSize) {
        log.info("Service layer:=============>PermissionServiceImpl.queryLikeName()");

        List<Permission> permissions = Lists.newArrayList();
        if (StringUtils.isNotEmpty(name)) {
            PageHelper.startPage(pageNum,pageSize);
            permissions = permissionMapper.queryLikeName(name);
        }
        return permissions;
    }

    @Override
    public Integer updateSelective(Permission permission) {
        log.info("Service layer:=============>PermissionServiceImpl.queryByIdsAndPagination()");

        Integer result = null;
        if (ObjectUtils.isNotNull(permission)) {
            result = permissionMapper.updateSelective(permission);
        }
        return result;
    }
}
