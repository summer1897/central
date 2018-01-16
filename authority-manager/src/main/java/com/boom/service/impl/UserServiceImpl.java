package com.boom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.dao.UserMapper;
import com.boom.domain.User;
import com.boom.service.IUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午10:25
 * @Description
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User>
                            implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByName(String userName) {
        return userMapper.queryByName(userName);
    }


    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public List<User> queryAllByPagination(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.queryAll();
    }
}
