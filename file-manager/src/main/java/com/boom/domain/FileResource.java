package com.boom.domain;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 * @Project file-manager
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午8:05
 * @Description 文件资源实体类
 */
public class FileResource extends BaseDomain<FileResource> implements Serializable {
    private static final long serialVersionUID = -4048505407902232240L;

    /**文件原名称*/
//    @TableField("origin_name")
    private String originName;
    /**文件新名称*/
//    @TableField("new_name")
    private String newName;
    /**文件扩展名*/
    private String extension;
    /**文件类型*/
    private String type;
    /**文件大小*/
    private Long size;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
