package com.boom.dao;

import com.SuperMapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/13 下午9:24
 * @Description 角色dao操作接口
 */
@Repository
public interface RoleMapper extends SuperMapper<Role> {

    /**
     * 根据角色名称查找角色对象
     * @param name 角色名称
     * @return {@link List<Role}
     */
    List<Role> queryLikeName(String name);

    /**
     * 查询所有角色对象
     * @return {@link List<Role>}
     */
    List<Role> queryAll();

}
