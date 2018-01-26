package com.boom.service.dto;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/26
 * @Time 14:19
 * @Description 简单角色实体类，仅仅包含Id和角色名称信息
 */
public class SimpleRoleDto implements Serializable {
    private static final long serialVersionUID = 2869795731204390367L;

    private Long id;
    private String name;

    public SimpleRoleDto() {
    }

    public SimpleRoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
