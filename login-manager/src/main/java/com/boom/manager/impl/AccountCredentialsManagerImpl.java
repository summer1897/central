package com.boom.manager.impl;

import com.boom.manager.IAccountCredentialsManager;
import com.boom.manager.IUserManager;
import com.boom.service.IAccountCredentialsService;
import com.boom.service.dto.AccountCredentialsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:46
 * @Description
 */
@Component("accountCredentialsManager")
public class AccountCredentialsManagerImpl implements IAccountCredentialsManager {

    private static final Logger logger = LoggerFactory.getLogger(AccountCredentialsManagerImpl.class);
    @Autowired
    private IAccountCredentialsService accountCredentialsService;
    @Autowired
    private IUserManager userManager;

    @Override
    public AccountCredentialsDto queryByUserName(String userName) {
        logger.info("Manager layer===============>AccountCredentialsManagerImpl.queryByUserName()");
        return accountCredentialsService.queryByUserName(userName);
    }

    @Override
    public List<String> queryRoles(Long id) {
        logger.info("Manager layer===============>AccountCredentialsManagerImpl.queryRoles()");
        return userManager.queryUserRoles(id);
    }

    @Override
    public List<String> queryPermissions(Long id) {
        logger.info("Manager layer===============>AccountCredentialsManagerImpl.queryPermissions()");
        return userManager.queryUserPermission(id);
    }
}
