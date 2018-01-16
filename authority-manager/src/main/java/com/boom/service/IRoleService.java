package com.boom.service;


import com.baomidou.mybatisplus.service.IService;
import com.boom.domain.Role;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/13 下午9:27
 * @Description 角色服务层操作接口
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据角色名称模糊查找角色对象
     * @param name 角色名称
     * @return Role
     */
    List<Role> queryLikeName(String name);

    /**
     * 查询所有角色对象
     * @return List<Role>
     */
    List<Role> queryAll();

    /**
     * 分页查询所有角色对象
     * @param pageNum 当前页起始下标
     * @param pageSize 当前页显示数据量
     * @return List<Role>
     */
    List<Role> queryAllByPagination(Integer pageNum, Integer pageSize);

}
