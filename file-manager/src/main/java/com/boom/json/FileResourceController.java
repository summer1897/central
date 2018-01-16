package com.boom.json;

import com.alibaba.fastjson.JSON;
import com.boom.domain.RoleFileResource;
import com.boom.service.IFileResourceService;
import com.boom.vo.ResultVo;
import com.summer.base.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/9
 * @Time 下午9:55
 * @Description 文件资源控制层操作
 */
@RestController
@RequestMapping("/file")
public class FileResourceController {

    private static final Logger logger = LoggerFactory.getLogger(FileResourceController.class);
    @Autowired
    private IFileResourceService fileResourceService;

    @GetMapping("/query.json/{id}")
    public ResultVo getById(@PathVariable Integer id) {
        logger.info("Controller layer:查询单个文件信息============>FileResourceController.queryById({})",id);

        RoleFileResource RoleFileResource = fileResourceService.selectById(id);
        if (ObjectUtils.isNotNull(RoleFileResource)) {
            return ResultVo.success(RoleFileResource);
        }

        return ResultVo.fail("查询文件信息失败");
    }

    @GetMapping("/lists.json/{roleId}")
    public ResultVo getByRoleId(@PathVariable String roleId) {
        logger.info("Controller layer:查询公司所有文件信息============>FileResourceController.getByRoleId({})",roleId);

        List<RoleFileResource> RoleFileResources = fileResourceService.queryByRoleId(roleId);
        if (ObjectUtils.isNotEmpty(RoleFileResources)) {
            return ResultVo.success(RoleFileResources);
        }

        return ResultVo.fail("查询公司文件信息失败");
    }

    @GetMapping("/delete.json/{id}")
    public ResultVo delete(@PathVariable Integer id) {
        logger.info("Controller layer:删除单个文件信息============>FileResourceController.delete({})",id);

        boolean success = fileResourceService.deleteById(id);
        if (success) {
            return ResultVo.success("文件删除成功");
        }

        return ResultVo.fail("文件删除失败");
    }

    @PostMapping("/file_name_modify.json")
    public ResultVo modifyFileName(@RequestBody RoleFileResource fileResource) {
        logger.info("Controller layer:修改文件名============>FileResourceController.getByRoleId({})",
                    JSON.toJSONString(fileResource,true));

        Integer result = fileResourceService.updateSelective(fileResource);
        if (ObjectUtils.isNotNull(result)) {
            return ResultVo.success("更新成功",result);
        }

        return ResultVo.fail("文件名修改失败失败");
    }

    @PostMapping("/upload.json")
    public ResultVo upload(MultipartFile file) {
        logger.info("Controller layer:上传文件信息============>FileResourceController.upload({})", JSON.toJSONString(file,true));

        return ResultVo.fail("上传文件失败");
    }

    @GetMapping("/download/{fileId}")
    public void download(@PathVariable Integer fileId, HttpServletResponse response) {
        logger.info("Controller layer:下载文件============>FileResourceController.download({})", fileId);

    }
}
