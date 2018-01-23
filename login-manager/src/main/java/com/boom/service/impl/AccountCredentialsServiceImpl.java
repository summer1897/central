package com.boom.service.impl;

import com.boom.dao.AccountCredentialsMapper;
import com.boom.domain.AccountCredentials;
import com.boom.service.IAccountCredentialsService;
import com.boom.service.dto.AccountCredentialsDto;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:36
 * @Description
 */
@Service("accountCredentialsService")
public class AccountCredentialsServiceImpl implements IAccountCredentialsService {

    private static final Logger log = LoggerFactory.getLogger(AccountCredentialsServiceImpl.class);
    @Autowired
    private AccountCredentialsMapper accountCredentialsMapper;

    @Override
    public AccountCredentialsDto queryByUserName(String userName) {
        log.info("Service layer==================>AccountCredentialsServiceImpl.queryByUserName()");

        AccountCredentials accountCredentials = null;
        AccountCredentialsDto accountCredentialsDto = null;
        if (StringUtils.isNotEmpty(userName)) {
            accountCredentials = accountCredentialsMapper.queryByUserName(userName);
        }
        if (ObjectUtils.isNotNull(accountCredentials)) {
            accountCredentialsDto = BeanCloneUtils.clone(accountCredentials,AccountCredentials.class,AccountCredentialsDto.class);
        }
        return accountCredentialsDto;
    }
}
