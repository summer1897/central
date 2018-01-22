package com.boom.controller.json;


import com.boom.domain.User;
import com.boom.service.IUserService;
import com.boom.utils.EncryptionUtils;
import com.boom.utils.JWTUtils;
import com.boom.utils.SecurityUtil;
import com.boom.vo.ResultVo;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by summer on 2017/12/12.
 */
@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login() {
        log.info("method:GET;path:/login.html");
        return "/login";
    }

    @PostMapping("/login.json")
    public ResultVo loginHandler(@RequestParam(value = "userName",required = true) String userName,
                                 @RequestParam(value = "password",required = true) String password) {
        log.info("method:POST;path:/login.html");

        User user = userService.queryByName(userName);

        if (null == user) {
            log.info("has no user");
            throw new UnknownAccountException();
        }

//        UsernamePasswordToken token = new UsernamePasswordToken(userName, EncryptionUtils.encrypt(password,user.getCredentialSalt()));
        //obtain the current subject
//        Subject currentUser = SecurityUtil.getSubject();

        try {
            log.info("验证当前用户");
            if (user.getPassword().equals(EncryptionUtils.encrypt(password,user.getCredentialSalt()))) {
                return ResultVo.success(200,ResultVo.SUCCESS_MSG,JWTUtils.sign(userName,password));
            }
//            currentUser.login(token);
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
        throw new UnknownAccountException();
    }
}
