package com.boom.dao;

import com.SuperMapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/14 下午10:13
 * @Description 权限DAO层操作接口定义
 */
@Repository
public interface PermissionMapper extends SuperMapper<Permission> {

    /**
     * 获取所有权限
     * @return List<Permission>
     */
    List<Permission> queryAll();

    /**
     * 查询当前权限的的所有子权限
     * @param permissionId
     * @return List<Permission>
     */
    List<Permission> queryChildren(Integer permissionId);

    /**
     * 根据多个权限ID查询其对应的所有子权限
     * @param permissionIds
     * @return
     */
    List<Permission> queryAllChildren(Set<Integer> permissionIds);

    /**
     * 根据权限名称模糊查询权限
     * @param name
     * @return List<Permission>
     */
    List<Permission> queryLikeName(String name);

    /**
     * 更新权限信息
     * @param permission
     * @return Integer,返回更新数据条数
     */
    Integer updateSelective(Permission permission);

}
