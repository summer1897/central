package com.boom.enums;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/23
 * @Time 11:36
 * @Description 登录用户状态信息
 */
public enum AccountSatus {

    UN_ACTIVATION_STATUS(0,"未激活"),
    NORMAL_STATUS(1,"正常"),
    FORBIDDEN_STATUS(2,"已禁用");

    /**
     * 状态码,0表示未激活，1表示正常，2表示已禁用
     */
    int status;
    /**
     * 状态描述
     */
    String description;


    private AccountSatus(int status,String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

}
