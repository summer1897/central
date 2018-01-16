package com.boom.exception;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/13 下午9:38
 * @Description 控制层异常类，用于Controller层异常的处理
 */
public class ControllerException extends RuntimeException {

    public ControllerException() {
        super();
    }

    public ControllerException(String msg) {
        super(msg);
    }

    public ControllerException(String msg,Throwable cause) {
        super(msg,cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String msg,Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(msg,cause,enableSuppression,writableStackTrace);
    }
}
