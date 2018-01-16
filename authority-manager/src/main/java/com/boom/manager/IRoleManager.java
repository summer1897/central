package com.boom.manager;

import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午4:54
 * @Description
 */
public interface IRoleManager {

    /**
     * 为角色添加权限
     * @param roleId
     * @param permissionIds
     * @return {@link boolean}
     */
    boolean authorize(Integer roleId, Set<Integer> permissionIds);

    /**
     *
     * @param id
     * @return {@link boolean}
     */
    boolean delete(Integer id);

    /**
     *
     * @param ids
     * @return {@link boolean}
     */
    boolean deleteBatch(Set<Integer> ids);

}
