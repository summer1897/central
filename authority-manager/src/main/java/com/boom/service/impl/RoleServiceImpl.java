package com.boom.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.dao.RoleMapper;
import com.boom.domain.Role;
import com.boom.exception.ServiceException;
import com.boom.service.IRoleService;
import com.boom.service.dto.SimpleRoleDto;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/13 下午9:30
 * @Description 服务层角色操作接口实现类
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService {

    private Logger log  = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Qualifier("roleMapper")
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<SimpleRoleDto> queryAllAvailableSimpleRoles() {
        log.info("Service layer=========>RoleServiceImpl.queryAllAvailableSimpleRoles()");

        EntityWrapper<Role> condition = new EntityWrapper<>();
        condition.eq("available",Role.AVAILABLE);
        List<Role> roles = roleMapper.selectList(condition);

        List<SimpleRoleDto> simpleRoles = Lists.newArrayList();
        if (ObjectUtils.isNotEmpty(roles)) {
            simpleRoles.addAll(BeanCloneUtils.clone(roles,Role.class,SimpleRoleDto.class));
        }
        return simpleRoles;
    }

    @Override
    public List<Role> queryLikeName(String name) throws ServiceException {
        log.info("Service layer=========>RoleServiceImpl.queryLikeName()");

        List<Role> roles = Lists.newArrayList();
        if (StringUtils.isNotEmpty(name)) {
            roles = roleMapper.queryLikeName(name);
        }
        return roles;
    }

    @Override
    public List<Role> queryAll() {
        log.info("Service layer=========>RoleServiceImpl.queryAll()");
        return roleMapper.queryAll();
    }

    @Override
    public List<Role> queryAllByPagination(Integer pageNum, Integer pageSize) {
        log.info("Service layer=========>RoleServiceImpl.queryAllByPagination()");
        PageHelper.startPage(pageNum,pageSize);
        return roleMapper.queryAll();
    }
}
