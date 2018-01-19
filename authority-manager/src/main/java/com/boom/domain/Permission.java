package com.boom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/7 下午9:40
 * @Description 权限实体类
 */
public class Permission extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -7191015071775255718L;

    /**父编号*/
    private Integer parentId;
    /**父编号列表*/
    private String parentIds;
    /**名称*/
    private String name;
    /**图标*/
    private String icon;
    /**资源类型（有菜单和按钮）*/
    private Integer type;
    /**资源路径，如果为空，则为顶级菜单*/
    private String url;
    /**权限字符串,如menu,role:*,button,role:create,role:delete,role:update,role:view*/
    private String permission;
    /**是否禁用，0表示禁用，1启用*/
    private Byte available = 0;
    /**排序序号*/
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
