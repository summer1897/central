package com.boom.dao;

import com.SuperMapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.User;
import org.apache.ibatis.annotations.Param;
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
     * 根据用户名模糊查询
     * @param userName
     * @return {@link User}
     */
    List<User> queryLikeUserName(@Param("userName") String userName);

    /**
     * 根据用户名才找用户
     * @param userName
     * @return @{com.boom.domain.User},否则，返回null
     */
    User queryByName(@Param("userName") String userName);

    /**
     * 查询所有用户
     * @return @{List<User>}
     */
    List<User> queryAll();

}
