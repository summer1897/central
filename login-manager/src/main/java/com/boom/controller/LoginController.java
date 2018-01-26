package com.boom.controller;


import com.alibaba.fastjson.JSON;
import com.boom.domain.AccountCredentials;
import com.boom.enums.AccountSatus;
import com.boom.enums.HttpStatus;
import com.boom.service.IAccountCredentialsService;
import com.boom.utils.EncryptionUtils;
import com.boom.utils.JWTUtils;
import com.boom.utils.SecurityUtil;
import com.boom.vo.ResultVo;
import com.summer.base.utils.ObjectUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by summer on 2017/12/12.
 */
@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IAccountCredentialsService accountCredentialsService;

    @GetMapping("/login.html")
    public String login() {
        log.info("method:GET;path:/login.html");
        return "/login.json";
    }

    @PostMapping("/login.json")
    public ResultVo loginHandler(@RequestBody AccountCredentials accountCredentials) {
        log.info("method:POST;path:/login.json======>LoginController.loginHandler({})",
                JSON.toJSONString(accountCredentials,true));

        String userName = ObjectUtils.isNotNull(accountCredentials) ? accountCredentials.getUserName() : null;
        String password = ObjectUtils.isNotNull(accountCredentials) ? accountCredentials.getPassword() : null;

        AccountCredentials account = accountCredentialsService.queryByUserName(userName);

        if (ObjectUtils.isNull(account)) {
            log.info("has no user");
            throw new UnknownAccountException("用户不存在");
        } else {
            Byte locked = account.getLocked();
            if (locked == AccountSatus.UN_ACTIVATION_STATUS.getStatus()) {
                throw new UnknownAccountException("用户未激活");
            } else if (locked == AccountSatus.FORBIDDEN_STATUS.getStatus()) {
                throw new UnknownAccountException("用户被禁用");
            }
        }

        log.warn("secret password:{}",EncryptionUtils.encrypt(password,account.getCredentialSalt()));

        UsernamePasswordToken token = new UsernamePasswordToken(userName, EncryptionUtils.encrypt(password,account.getCredentialSalt()));
//        obtain the current subject
        Subject currentUser = SecurityUtil.getSubject();

        try {
            currentUser.login(token);

            //返回用户token
            return ResultVo.success(HttpStatus.STATUS_OK,JWTUtils.sign(userName,JWTUtils.SECRET));
        } catch (UnknownAccountException uae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
        } catch (LockedAccountException lae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
        } catch (AuthenticationException ae) {
            log.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
        }
        throw new UnknownAccountException("用户密码错误");
    }
}
