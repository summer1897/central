package com.boom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.controller.vo.UserVo;
import com.boom.dao.UserMapper;
import com.boom.domain.User;
import com.boom.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long queryTotal() {
        log.info("Service layer========================>UserServiceImpl.queryTotal()");
        return userMapper.queryTotal();
    }

    @Override
    public List<User> queryLikeUserName(String userName) {
        log.info("Service layer========================>UserServiceImpl.queryLikeUserName()");

        List<User> users = Lists.newArrayList();
        if (StringUtils.isNotEmpty(userName)) {
            users = userMapper.queryLikeUserName(userName);
        }
        return users;
    }

    @Override
    public User queryByName(String userName) {
        log.info("Service layer========================>UserServiceImpl.queryByName()");

        User user = null;
        if (StringUtils.isNotEmpty(userName)) {
            user = userMapper.queryByName(userName);
        }
        return user;
    }


    @Override
    public List<User> queryAll() {
        log.info("Service layer========================>UserServiceImpl.queryAll()");
        return userMapper.queryAll();
    }

    @Override
    public List<User> queryAllByPagination(Integer pageNum, Integer pageSize) {
        log.info("Service layer=========>UserServiceImpl.queryAllByPagination()");
        PageHelper.startPage(pageNum,pageSize);
        return this.queryAll();
    }
}
