package com.boom.domain;

import java.io.Serializable;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午8:21
 * @Description 系统文件资源类，用于存放在本地文件系统中
 */
public class SystemFileResource extends FileResource implements Serializable {

    private static final long serialVersionUID = -938588932068903818L;

    /**文件存放在本地操作系统中的绝对路径*/
//    @TableField("save_path")
    private String savePath;

    public SystemFileResource() {
        super();
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
