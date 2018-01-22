package com.boom.service;

import com.baomidou.mybatisplus.service.IService;
import com.boom.domain.RoleFileResource;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午8:37
 * @Description 文件资源服务层操作接口定义
 */
public interface IFileResourceService extends IService<RoleFileResource> {


    /**
     *
     * @param companyId
     * @param fileName 文件名称
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryLikeByName(Integer companyId, String fileName);

    /**
     *
     * @param roleId 公司唯一标识ID
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByRoleId(Long roleId);

    /**
     *
     * @param companyId
     * @param modifyDate 文件最近修改时间
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByModifyDate(Integer companyId, Date modifyDate);

    /**
     *
     * @param companyId
     * @param uploadDate 文件上传时间
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByUploadDate(Integer companyId, Date uploadDate);

    /**
     *
     * @param companyId
     * @param extension 文件扩展名
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByExtension(Integer companyId, String extension);


    /**
     * 插入非空属性字段
     * @param RoleFileResource
     * @return {@link Integer}
     */
    Integer inserSelective(RoleFileResource RoleFileResource);

    /**
     * 更新非空属性字段
     * @param RoleFileResource
     * @return {@link Integer}
     */
    Integer updateSelective(RoleFileResource RoleFileResource);

}
