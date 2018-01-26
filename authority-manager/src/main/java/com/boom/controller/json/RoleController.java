package com.boom.controller.json;

import com.alibaba.fastjson.JSON;
import com.boom.domain.Role;
import com.boom.enums.HttpStatus;
import com.boom.manager.IRoleManager;
import com.boom.service.IRoleService;
import com.boom.service.dto.SimpleRoleDto;
import com.boom.vo.ResultVo;
import com.github.pagehelper.PageInfo;
import com.summer.base.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2017/12/13 下午9:53
 * @Description 角色操作控制层
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleManager roleManager;

    @GetMapping("/query_all_available.json")
    public ResultVo getAllAvailable() {
        log.info("Controller layer:查询所有可用角色信息===>RoleController.list()");

        List<SimpleRoleDto> simpleRoleDtos = roleService.queryAllAvailableSimpleRoles();
        if (ObjectUtils.isNotEmpty(simpleRoleDtos)) {
            return ResultVo.success(HttpStatus.STATUS_OK,simpleRoleDtos);
        }
        return ResultVo.fail("暂无角色信息");
    }

    @GetMapping(value = "/query_by_name.json/{name}")
    public ResultVo get(@PathVariable("name") String name) {
        log.info("Controller layer:根据角色名称查询角色对象===>RoleController.list({})",name);

        List<Role> roles = roleService.queryLikeName(name);
        if (ObjectUtils.isNotEmpty(roles)) {
            return ResultVo.success(roles);
        }
        return ResultVo.fail("没有查询到角色信息");
    }

    @GetMapping(value = "/lists.json")
    public ResultVo lists() {
        log.info("Controller layer:查询所有角色对象===>RoleController.lists()");

        List<Role> roles = roleService.queryAll();
        if (ObjectUtils.isNotEmpty(roles)) {
            return ResultVo.success(roles);
        }
        return ResultVo.fail("没有查询到角色信息");
    }

    @GetMapping(value = "/listsPage.json/{pageNum}/{pageSize}")
    public ResultVo listsByPagination(@PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize) {
        log.info("Controller layer:分页查询角色对象,pageNum={},pageSize={}===>RoleController.listsByPagination()",pageNum,pageSize);

        List<Role> roles = roleService.queryAllByPagination(pageNum, pageSize);
        if (ObjectUtils.isNotEmpty(roles)) {
            PageInfo<Role> pageInfo = new PageInfo<>(roles);
            return ResultVo.success(pageInfo);
        }
        return ResultVo.fail("没有查询到角色信息");
    }

    @PostMapping("/add.json")
    public ResultVo add(Role role) {
        log.info("Controller layer:添加角色===>RoleController.add({})", JSON.toJSONString(role,true));

        boolean success = roleService.insert(role);
        if (success) {
            return ResultVo.success("添加角色成功");
        }
        return ResultVo.fail("添加角色失败");
    }

    @PostMapping("/update.json")
    public ResultVo update(Role role) {
        log.info("Controller layer:更新角色===>RoleController.update({})", JSON.toJSONString(role,true));

        boolean success = roleService.updateById(role);
        if (success) {
            return ResultVo.success("更新角色成功");
        }
        return ResultVo.fail("更新角色失败");
    }

    @GetMapping("/delete.json/{id}")
    public ResultVo delete(@PathVariable Long id) {
        log.info("Controller layer:删除角色===>RoleController.delete({})", id);

        boolean success = roleManager.delete(id);
        if (success) {
            return ResultVo.success("删除角色成功");
        }
        return ResultVo.fail("删除角色失败");
    }

    @GetMapping("/deletes.json/{id}")
    public ResultVo deleteBatch(@PathVariable Set<Long> ids) {
        log.info("Controller layer:删除角色===>RoleController.deletes({})", ids);

        boolean success = roleManager.deleteBatch(ids);
        if (success) {
            return ResultVo.success("删除所选角色成功");
        }
        return ResultVo.fail("删除所选角色失败");
    }


//    @ApiOperation(notes = "授权",value = "为角色授权")
    @GetMapping("/authorize.json/{roleId}/{permissionIds}")
    public ResultVo authorize(@PathVariable Long roleId,@PathVariable Set<Long> permissionIds) {
        log.info("Controller layer:Controller layer:为角色授权===>RoleController.deletes({},{})", roleId,permissionIds);

        boolean success = roleManager.authorize(roleId,permissionIds);
        if (success) {
            return ResultVo.success("授权成功");
        }
        return ResultVo.fail("授权失败");
    }

}
