package com.boom.service;


import com.baomidou.mybatisplus.service.IService;
import com.boom.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午10:19
 * @Description 用户操作接口
 */
public interface IUserService extends IService<User> {

    /**
     *
     * @return {@link Long}
     */
    Long queryTotal();

    /**
     * 根据用户名模糊查询
     * @param userName
     * @return {@link User}
     */
    List<User> queryLikeUserName(@Param("userName") String userName);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return {@link User},否则，返回null
     */
    User queryByName(String userName);

    /**
     * 根据用户邮箱查找用户
     * @param email
     * @return {@link User},否则，返回null
     */
    User queryByEmail(String email);

    /**
     * 根据用户手机号查找用户
     * @param phone
     * @return {@link User},否则，返回null
     */
    User queryByPhone(String phone);

    /**
     * 查询所有用户
     * @return {@link List<User>}
     */
    List<User> queryAll();

    /**
     * 分页查询所有用户信息
     * @param pageNum 当前页其实位置
     * @param pageSize 当前页显示数据量
     * @return {@link List<User>}
     */
    List<User> queryAllByPagination(Integer pageNum, Integer pageSize);

}
