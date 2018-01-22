package com.boom.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午8:07
 * @Description
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户ID查询所有角色ID
     * @param userId
     * @return {@link List <Long>}
     */
    List<Long> queryByUserId(@Param("userId") Long userId);

    /**
     *
     * @param userId
     * @param roleIds
     * @return {@link Integer}
     */
    Integer add(@Param("userId") Long userId, @Param("roleIds") Set<Long> roleIds);

    /**
     *
     * @param userId
     * @param roleIds
     * @return {@link Integer}
     */
    Integer delete(@Param("userId") Long userId, @Param("roleIds") Set<Long> roleIds);

}
