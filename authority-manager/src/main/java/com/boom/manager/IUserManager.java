package com.boom.manager;

import com.boom.service.dto.Node;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午7:57
 * @Description 用户关联操作接口定义
 */
public interface IUserManager {

    /**
     * 查询用户拥有的菜单信息
     * @param userId
     * @return {@link List<Node>}
     */
    List<Node> queryTreeMenu(Long userId);

    /**
     * 查询用户权限信息
     * @param userId
     * @return {@link List<String>}
     */
    List<String> queryUserPermission(Long userId);

    /**
     * 查询用户拥有的角色信息
     * @param userId
     * @return List<String>
     */
    List<String> queryUserRoles(Long userId);

    /**
     * 用户添加角色
     * @param userId
     * @param roleIds
     * @return {@link boolean}
     */
    boolean addRoles(Long userId, Set<Long> roleIds);

    /**
     * 用户角色删除
     * @param userId
     * @param roleIds
     * @return {@link boolean}
     */
    boolean deleteRoles(Long userId, Set<Long> roleIds);

}
