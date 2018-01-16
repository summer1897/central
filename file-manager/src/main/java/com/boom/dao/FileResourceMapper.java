package com.boom.dao;

import com.SuperMapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boom.domain.RoleFileResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午8:27
 * @Description 文件资源数据访问层操作接口定义
 */
@Repository
public interface FileResourceMapper extends SuperMapper<RoleFileResource> {

    /**
     *
     * @param fileName 文件名称
     * @param companyId
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryLikeByName(@Param("companyId") Integer companyId,
                                           @Param("fileName") String fileName);

    /**
     *
     * @param roleId 公司唯一标识ID
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByRoleId(@Param("roleId") String roleId);

    /**
     *
     * @param companyId
     * @param modifyDate 文件最近修改时间
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByModifyDate(@Param("companyId") Integer companyId,
                                             @Param("modifyDate") Date modifyDate);

    /**
     *
     * @param companyId
     * @param uploadDate 文件上传时间
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByUploadDate(@Param("companyId") Integer companyId,
                                             @Param("uploadDate") Date uploadDate);

    /**
     *
     * @param companyId
     * @param extension 文件扩展名
     * @return {@link List<RoleFileResource>}
     */
    List<RoleFileResource> queryByExtension(@Param("companyId") Integer companyId,
                                            @Param("extension") String extension);

    /**
     * 插入非空属性字段
     * @param specialFileResource
     * @return {@link Integer}
     */
    Integer insertSelective(RoleFileResource specialFileResource);


    /**
     * 更新非空属性字段
     * @param specialFileResource
     * @return {@link Integer}
     */
    Integer updateSelective(RoleFileResource specialFileResource);

}
