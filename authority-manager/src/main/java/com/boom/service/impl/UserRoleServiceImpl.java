package com.boom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.dao.UserRoleMapper;
import com.boom.domain.UserRole;
import com.boom.service.IUserRoleService;
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
 * @Date 2018/1/14 下午8:04
 * @Description
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements IUserRoleService {

    private static final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> queryRoles(Long userId) {
        return null;
    }

    @Override
    public List<Long> queryByUserId(Long userId) {
        log.info("Service layer========>UserRoleServiceImpl.queryByUserId()");

        List<Long> roleIds = Lists.newArrayList();
        if (ObjectUtils.isNotNull(userId)) {
            roleIds = userRoleMapper.queryByUserId(userId);
        }
        return roleIds;
    }

    @Override
    public boolean add(Long userId, Set<Long> roleIds) {
        log.info("Service layer========>UserRoleServiceImpl.add()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(userId) && ObjectUtils.isNotEmpty(roleIds)) {
            isSuccess = retBool(userRoleMapper.add(userId,roleIds));
        }
        return isSuccess;
    }

    @Override
    public boolean delete(Long userId, Set<Long> roleIds) {
        log.info("Service layer========>UserRoleServiceImpl.delete()");

        boolean isSuccess = false;
        if (ObjectUtils.isNotNull(userId) && ObjectUtils.isNotEmpty(roleIds)) {
            isSuccess = retBool(userRoleMapper.delete(userId,roleIds));
        }
        return isSuccess;
    }
}
