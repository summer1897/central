package com.boom.utils;

import com.boom.exception.ControllerException;
import com.boom.exception.ServiceException;
import com.boom.vo.ResultVo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/16
 * @Time 16:57
 * @Description 系统异常统一处理类
 */
@ControllerAdvice
public class ExceptionHandlers {

    /**
     * 控制层异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ControllerException.class)
    public ResultVo controllerExceptionHandler(ControllerException e) {
        return ResultVo.fail("前端请求异常");
    }

    /**
     * 服务层异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResultVo serviceExceptionHandler(ServiceException e) {
        return ResultVo.fail("业务逻辑层异常");
    }

    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public ResultVo unauthenticatedExceptionHandler(UnauthenticatedException e) {
        return ResultVo.fail("未授权");
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public ResultVo AuthorizationExceptionHandler(AuthorizationException e) {
        return ResultVo.fail(e.getMessage());
    }

}
