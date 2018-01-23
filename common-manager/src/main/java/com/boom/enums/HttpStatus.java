package com.boom.enums;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/23 下午8:49
 * @Description Http状态码
 */
public enum HttpStatus {

    STATUS_OK(200,"成功","ok"),
    STATUS_400(400,"错误请求","Bad Request"),
    STATUS_401(401,"未授权","Unauthorized"),
    STATUS_403(403,"禁止访问","Forbidden"),
    STATUS_404(404,"为找着请求页面","Not Found"),
    STATUS_405(405,"未允许调用此方法","Method Not Allowed"),
    STATUS_500(500,"服务器出错","Internal Server Error"),
    STATUS_502(502,"请求网管出错","Bad Gateway"),
    STATUS_503(503,"请求服务不可用","Service Unavailable"),
    STATUS_505(504,"请求超时","Gateway Timeout");

    /**
     * 状态吗
     */
    private int code;
    /**
     * 状态码描述
     */
    private String msg;
    /**
     * 状态码英文描述
     */
    private String emsg;

    private HttpStatus(int code,String msg,String emsg) {
        this.code = code;
        this.msg = msg;
        this.emsg = emsg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getEmsg() {
        return this.emsg;
    }

}
