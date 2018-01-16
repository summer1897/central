package com.boom.service;

import com.baomidou.mybatisplus.service.IService;
import com.boom.domain.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午8:03
 * @Description 用户角色关联操作服务层接口定义
 */
public interface IUserRoleService extends IService<UserRole> {

    List<UserRole> queryRoles(Integer userId);

    /**
     * 根据用户ID查询所有角色ID
     * @param userId
     * @return {@link List<Integer>}
     */
    List<Integer> queryByUserId(Integer userId);

    /**
     *
     * @param userId
     * @param roleIds
     * @return {@link boolean}
     */
    boolean add(Integer userId, Set<Integer> roleIds);

    /**
     *
     * @param userId
     * @param roleIds
     * @return {@link boolean}
     */
    boolean delete(Integer userId, Set<Integer> roleIds);

}
