package com.boom.dao;

import com.boom.domain.AccountCredentials;
import org.springframework.stereotype.Repository;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 10:25
 * @Description
 */
@Repository
public interface AccountCredentialsMapper {

    /**
     *
     * @param userName
     * @return {@link AccountCredentials}
     */
    AccountCredentials queryByUserName(String userName);

}
