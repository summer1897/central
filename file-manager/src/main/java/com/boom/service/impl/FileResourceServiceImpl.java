package com.boom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boom.dao.FileResourceMapper;
import com.boom.domain.RoleFileResource;
import com.boom.service.IFileResourceService;
import com.google.common.collect.Lists;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午8:38
 * @Description {@link IFileResourceService}实现类
 */
@Service("fileResourceService")
@Transactional
public class FileResourceServiceImpl extends ServiceImpl<FileResourceMapper,RoleFileResource>
                                        implements IFileResourceService {

    private static final Logger logger = LoggerFactory.getLogger(FileResourceServiceImpl.class);

    @Autowired
    @Qualifier("fileResourceMapper")
    private FileResourceMapper fileResourceMapper;


    @Override
    public List<RoleFileResource> queryLikeByName(Integer companyId, String fileName) {
        logger.info("Service layer:通过文件名查询文件资源信息==========>FileResourceServiceImpl.queryLikeByName()");

        List<RoleFileResource> RoleFileResources = Lists.newArrayList();
        if (StringUtils.isNotEmpty(fileName) && ObjectUtils.isNotNull(companyId)) {
            RoleFileResources = fileResourceMapper.queryLikeByName(companyId, fileName);
        }
        return RoleFileResources;
    }

    @Override
    public List<RoleFileResource> queryByRoleId(String roleId) {
        logger.info("Service layer:查询某公司拥有的文件资源信息==========>FileResourceServiceImpl.queryByCompanyId()");

        List<RoleFileResource> RoleFileResources = Lists.newArrayList();
        if (ObjectUtils.isNotNull(roleId)) {
            RoleFileResources = fileResourceMapper.queryByRoleId(roleId);
        }
        return RoleFileResources;
    }

    @Override
    public List<RoleFileResource> queryByModifyDate(Integer companyId, Date modifyDate) {
        logger.info("Service layer:查询某个时间修改的文件资源信息==========>FileResourceServiceImpl.queryByModifyDate()");

        List<RoleFileResource> RoleFileResources = Lists.newArrayList();
        if (ObjectUtils.isNotNull(companyId)) {
            if (ObjectUtils.isNull(modifyDate)) {
                modifyDate = new Date();
            }
            RoleFileResources = fileResourceMapper.queryByModifyDate(companyId, modifyDate);
        }
        return RoleFileResources;
    }

    @Override
    public List<RoleFileResource> queryByUploadDate(Integer companyId, Date uploadDate) {
        logger.info("Service layer:查询某个上传时间的文件资源信息==========>FileResourceServiceImpl.queryByUploadDate()");

        List<RoleFileResource> RoleFileResources = Lists.newArrayList();
        if (ObjectUtils.isNotNull(companyId)) {
            if (ObjectUtils.isNull(uploadDate)) {
                uploadDate = new Date();
            }
            RoleFileResources = fileResourceMapper.queryByUploadDate(companyId, uploadDate);
        }
        return RoleFileResources;
    }

    @Override
    public List<RoleFileResource> queryByExtension(Integer companyId, String extension) {
        logger.info("Service layer:根据文件扩展名查询文件资源信息==========>FileResourceServiceImpl.queryByExtension()");

        List<RoleFileResource> RoleFileResources = Lists.newArrayList();
        if (ObjectUtils.isNotNull(companyId) && StringUtils.isNotEmpty(extension)) {
            RoleFileResources = fileResourceMapper.queryByExtension(companyId, extension);
        }
        return RoleFileResources;
    }


    @Override
    public Integer inserSelective(RoleFileResource RoleFileResource) {
        logger.info("Service layer:添加文件资源信息===============>FileResourceServiceImpl.insert()");

        Integer result = null;
        if (ObjectUtils.isNotNull(RoleFileResource)) {
            result = fileResourceMapper.insertSelective(RoleFileResource);
        }
        return result;
    }


    @Override
    public Integer updateSelective(RoleFileResource RoleFileResource) {
        logger.info("Service layer:更新文件资源信息===============>FileResourceServiceImpl.update()");

        Integer result = null;
        if (ObjectUtils.isNotNull(RoleFileResource)) {
            result = fileResourceMapper.updateSelective(RoleFileResource);
        }
        return result;
    }
}