package com.boom.exception;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/13 下午9:35
 * @Description 服务层异常类,用于服务层异常处理
 */
public class ServiceException extends RuntimeException{

    public ServiceException(){
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg,Throwable cause) {
        super(msg,cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String msg, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(msg,cause,enableSuppression,writableStackTrace);
    }

}
