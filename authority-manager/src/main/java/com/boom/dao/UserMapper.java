package com.boom.dao;

import com.SuperMapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午10:25
 * @Description User dao层操作接口
 */
@Repository
public interface UserMapper extends SuperMapper<User> {

    /**
     * 根据用户名才找用户
     * @param userName
     * @return @{com.boom.rbac.domain.User},否则，返回null
     */
    User queryByName(String userName);

    /**
     * 根据用户名和密码查询用户
     * @param userName
     * @param password
     * @return @{com.boom.rbac.domain.User},否则返回null
     */
    User queryUser(String userName, String password);

    /**
     * 查询所有用户
     * @return @{List<User>}
     */
    List<User> queryAll();

}
