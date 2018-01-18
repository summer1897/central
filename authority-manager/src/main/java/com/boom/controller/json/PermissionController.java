package com.boom.controller.json;

import com.boom.domain.Permission;
import com.boom.service.IPermissionService;
import com.boom.utils.TreeNode;
import com.boom.vo.ResultVo;
import com.github.pagehelper.PageInfo;
import com.summer.base.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/14 下午11:54
 * @Description 权限控制层操作
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private static final Logger log = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private IPermissionService permissionService;

    @GetMapping(value = "/lists.json")
    public ResultVo getAll() {
        log.info("Controller layer:查询所有权限信息=========>PermissionController.listAll");

        List<Permission> permissions = permissionService.queryAll();
        if (ObjectUtils.isNotEmpty(permissions)) {
            return ResultVo.success(permissions);
        }
        return ResultVo.fail("查询权限信息失败");
    }

    @GetMapping(value = "/lists_tree.json")
    public ResultVo getAllTree() {
        log.info("Controller layer:将所有权限信息以树形的形式返回===============>PermissionController.getAllTree()");

        TreeNode treeNode = new TreeNode(permissionService.queryAll());
        if (ObjectUtils.isNotNull(treeNode)) {
            return ResultVo.success(treeNode.buildTree());
        }
        return ResultVo.fail("查询树形权限信息失败");
    }

    @GetMapping(value = "/lists.json/{pageNum}/{pageSize}")
    public ResultVo getAllAndPagination(@PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("pageSize") Integer pageSize) {
        log.info("Controller layer:分页查询所有权限信息=========>PermissionController.listAllAndPagination({},{})",
                pageNum,pageSize);

        List<Permission> permissions = permissionService.queryAllAndPagination(pageNum,pageSize);
        if (ObjectUtils.isNotEmpty(permissions)) {
            PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
            return ResultVo.success(pageInfo);
        }
        return ResultVo.fail("查询权限信息失败");
    }
}
