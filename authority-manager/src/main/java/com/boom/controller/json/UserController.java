package com.boom.controller.json;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.boom.controller.vo.UserVo;
import com.boom.domain.User;
import com.boom.enums.HttpStatus;
import com.boom.manager.IUserManager;
import com.boom.service.IUserService;
import com.boom.service.dto.Node;
import com.boom.service.dto.SimpleRoleDto;
import com.boom.utils.EncryptionUtils;
import com.boom.utils.MapBuilder;
import com.boom.utils.MapUtils;
import com.boom.utils.RandomIdGenerator;
import com.boom.vo.ResultVo;
import com.github.pagehelper.PageInfo;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserManager userManager;

    @GetMapping("/total.json")
    public ResultVo queryTotal() {
        log.info("Controller layer:用户总数统计===>UserController.queryTotal()");

        Long total = userService.queryTotal();
        if (ObjectUtils.isNotNull(total)) {
            return ResultVo.success(HttpStatus.STATUS_OK, MapUtils.builder().putVal("total",total));
        }
        return ResultVo.fail("暂无用户信息");
    }

    @GetMapping("/query.json/{name}")
    public ResultVo list(@PathVariable("name") String name) {
        log.info("query user info by name===>UserController.list()");

        User user =  userService.queryByName(name);
        if (ObjectUtils.isNotNull(user)) {
            ResultVo.success(HttpStatus.STATUS_OK,BeanCloneUtils.clone(user,User.class,UserVo.class));
        }
        return ResultVo.fail("没有找到改用户信息");
    }

    @GetMapping(value = "/lists.json")
    public ResultVo lists() {
        log.info("method:GET ===> request path:/user/list===>UserController.list()");

        List<User> users = userService.queryAll();
        if (ObjectUtils.isNotEmpty(users)) {
//            log.warn("uservo infos:{}",JSON.toJSONString(BeanCloneUtils.deepClone(users,User.class,UserVo.class),true));
            return ResultVo.success(HttpStatus.STATUS_OK,BeanCloneUtils.deepClone(users,User.class,UserVo.class));
        }
        return ResultVo.fail("没有查到用户信息");
    }

    @GetMapping(value = "/lists_by_pagination.json/{pageNum}/{pageSize}")
    public ResultVo lists(@PathVariable Integer pageNum,@PathVariable Integer pageSize) {
        log.info("method:GET ===> request path:/user/list===>UserController.list({},{})",pageNum,pageSize);

        List<User> users = userService.queryAllByPagination(pageNum,pageSize);
        if (ObjectUtils.isNotEmpty(users)) {
            return ResultVo.success(HttpStatus.STATUS_OK,new PageInfo<>(BeanCloneUtils.clone(users,User.class,UserVo.class)));
        }
        return ResultVo.fail("没有查到用户信息");
    }

    @GetMapping("/query_by_username.json/{userName}")
    public ResultVo getByUserName(@PathVariable String userName) {
        log.info("Controller layer:根据用户名查询用户信息===>UserController.getByUserName({})",userName);

        User user = userService.queryByName(userName);
        if (ObjectUtils.isNotNull(user)) {
            return ResultVo.success(HttpStatus.STATUS_OK,BeanCloneUtils.clone(user,User.class,UserVo.class));
        }
        return ResultVo.fail("没有查到用户信息");
    }

    @GetMapping("/query_like_username.json/{userName}")
    public ResultVo getLikeUserName(@PathVariable String userName) {
        log.info("Controller layer:根据用户名模糊查询用户信息===>UserController.getLikeUserName({})",userName);

        List<User> users = userService.queryLikeUserName(userName);
        if (ObjectUtils.isNotEmpty(users)) {
            return ResultVo.success(HttpStatus.STATUS_OK,BeanCloneUtils.clone(users,User.class,UserVo.class));
        }
        return ResultVo.fail("没有查到用户信息");
    }

    @GetMapping(value = "/menus.json")
    public ResultVo getUserMenu() {
        log.info("method:GET===>/user/list===>UserController.getMenu()");

        List<Node> menus = userManager.queryTreeMenu(1l);
        if (ObjectUtils.isNotEmpty(menus)) {
            return ResultVo.success(HttpStatus.STATUS_OK,menus);
        }
        return ResultVo.fail("没有查到用户菜单信息");
    }

    @PostMapping("/add.json")
    public ResultVo add(@RequestBody User user) {
        log.info("Controller layer:添加用户===============>UserController.add({})",
                JSON.toJSONString(user,true));

        this.encrypt(user);
        boolean success = userService.insert(user);
        if (success) {
            return ResultVo.success("添加用户成功");
        }
        return ResultVo.fail("添加用户失败");
    }

    @GetMapping("/delete.json/{id}")
    public ResultVo delete(@PathVariable Long id) {
        log.info("Controller layer:删除用户===============>UserController.delete({})",id);

        boolean success = userService.deleteById(id);
        if (success) {
            return ResultVo.success("删除用户成功");
        }
        return ResultVo.fail("删除用户失败");
    }

    @GetMapping("/delete_batch.json/{ids}")
    public ResultVo deleteBatch(@PathVariable Set<Long> ids) {
        log.info("Controller layer:批量删除用户===============>UserController.deleteBatch({})",ids);

        boolean success = userService.deleteBatchIds(ids);
        if (success) {
            return ResultVo.success("删除所选用户成功");
        }
        return ResultVo.fail("删除所选用户失败");
    }

    @PostMapping("/update.json")
    public ResultVo update(@RequestBody User user) {
        log.info("Controller layer:更新用户信息===============>UserController.update({})",
                JSON.toJSONString(user,true));

        if (ObjectUtils.isNotNull(user) && StringUtils.isNotEmpty(user.getPassword())) {
            this.encrypt(user);
        }
        boolean success = userService.updateById(user);
        if (success) {
            return ResultVo.success("更新用户成功");
        }
        return ResultVo.fail("更新用户失败");
    }

    @GetMapping("/add_roles.json/{roleIds}")
    public ResultVo addRoles(@PathVariable Set<Long> roleIds) {
        log.info("Controller layer:用户角色添加===============>UserController.addRoles({})",roleIds);

        boolean success = userManager.addRoles(null,roleIds);
        if (success) {
            return ResultVo.success("用户角色添加成功");
        }
        return ResultVo.fail("用户角色添加失败");
    }

    @GetMapping("/delete_roles.json/{roleIds}")
    public ResultVo deleteRoles(@PathVariable Set<Long> roleIds) {
        log.info("Controller layer:用户角色删除===============>UserController.add({})",roleIds);

        boolean success = userManager.deleteRoles(null,roleIds);
        if (success) {
            return ResultVo.success("用户角色删除成功");
        }
        return ResultVo.fail("用户角色删除失败");
    }

    @GetMapping("/query_user_roles.json/{userId}")
    public ResultVo getUserRoles(@PathVariable Long userId) {
        log.info("Controller layer:查询用户角色===============>UserController.getUserRoles({})",userId);

        List<SimpleRoleDto> simpleRoles = userManager.queryUserRoles(userId);
        if (ObjectUtils.isNotEmpty(simpleRoles)) {
            return ResultVo.success(HttpStatus.STATUS_OK,simpleRoles);
        }
        return ResultVo.fail("用户没有角色信息");
    }

    @GetMapping("/query_user_permissions.json/{userId}")
    public ResultVo getUserPermission(@PathVariable Long userId) {
        log.info("Controller layer:查询用户权限===============>UserController.getUserPermission({})",userId);

        List<String> permissions = userManager.queryUserPermission(userId);
        if (ObjectUtils.isNotEmpty(permissions)) {
            return ResultVo.success(HttpStatus.STATUS_OK,permissions);
        }
        return ResultVo.fail("用户没有权限信息");
    }

    private void encrypt(User user) {
        if (ObjectUtils.isNotNull(user)) {
            user.setSalt(RandomIdGenerator.geneateUUID().toString());
            String password = user.getPassword();
            String credentialSalt = user.getCredentialSalt();
            user.setPassword(EncryptionUtils.encrypt(password,credentialSalt));
        }
    }
}
