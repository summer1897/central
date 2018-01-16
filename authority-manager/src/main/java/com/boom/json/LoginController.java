package com.boom.json;


import com.boom.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by summer on 2017/12/12.
 */
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        log.info("method:GET;path:/login.html");
        return "/login";
    }

    @RequestMapping(value = "/login.html",method = RequestMethod.POST)
    public String loginHandler(@RequestParam(value = "userName",required = true) String userName,
                               @RequestParam(value = "password",required = true) String password) {
        log.info("method:POST;path:/login.html");

        /*User user = userService.queryByName(userName);

        if (null == user) {
            log.info("has no user");
            throw new UnknownAccountException();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(userName, EncryptionUtils.encrypt(password,user.getCredentialSalt()));
        //obtain the current subject
        Subject currentUser = SecurityUtil.getSubject();

        try {
            log.info("验证当前用户");
            currentUser.login(token);
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

        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            log.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            return "redirect:/user/lists.json";
        }else{
            token.clear();
            return "redirect:/login";
        }*/
        return "";
    }
}
