package com.boom.controller;

import com.auth0.jwt.JWT;
import com.boom.utils.JWTUtils;
import com.boom.utils.RandomIdGenerator;
import com.boom.utils.SecurityUtil;
import com.boom.vo.ResultVo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 14:22
 * @Description
 */
@RestController
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/sayHello.json")
    public Object sayHello() {
        return "Hello";
    }

    @PostMapping("/auth/token.json")
    public ResultVo applyToken(@RequestParam(name = "clientId",required = true) String clientId,
                               @RequestParam(name = "password",required = true) String password) {
        log.info("Controller layer:登录并申请Token=============>AuthenticationController.applyToken({})",clientId);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(clientId,password);
        Subject subject = SecurityUtil.getSubject();
        subject.login(usernamePasswordToken);
        // 签发一个Json Web Token
        // 令牌ID=uuid，用户=clientKey，签发者=clientKey
        // token有效期=1分钟，用户角色=null,用户权限=create,read,update,delete
        String token = JWTUtils.applyToken(RandomIdGenerator.geneateUUID().toString(),clientId,
                            "token-server",null,"admin","create,delete,update,read");
        return ResultVo.success(ResultVo.SUCCESS_MSG,token);
    }

}
