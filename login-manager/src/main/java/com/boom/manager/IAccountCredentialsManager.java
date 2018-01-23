package com.boom.manager;

import com.boom.service.dto.AccountCredentialsDto;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:42
 * @Description 对外提供用户信息接口定义
 */
public interface IAccountCredentialsManager {

    AccountCredentialsDto queryByUserName(String userName);

    List<String> queryRoles(Long id);

    List<String> queryPermissions(Long id);
}
