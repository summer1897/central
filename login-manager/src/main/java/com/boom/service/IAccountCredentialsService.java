package com.boom.service;

import com.boom.domain.AccountCredentials;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:24
 * @Description 用户登录服务层操作接口定义
 */
public interface IAccountCredentialsService {

    /**
     *
     * @param userName
     * @return {@link AccountCredentials}
     */
    AccountCredentials queryByUserName(String userName);

}
