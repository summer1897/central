package com.boom.utils;

import com.boom.vo.Principal;
import com.summer.base.utils.ObjectUtils;
import org.apache.shiro.SecurityUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/16
 * @Time 16:16
 * @Description 用户认证工具
 */
public class SecurityUtil {

    public static Principal getPrincipal() {
        return (Principal) SecurityUtils.getSubject().getPrincipal();
    }

    public static boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static boolean isRemembered() {
        return SecurityUtils.getSubject().isRemembered();
    }

    public static boolean hasRole(String role) {
        return SecurityUtils.getSubject().hasRole(role);
    }

    /**
     * 同时具有多个角色
     * @param roles
     * @return
     */
    public static boolean hasRoles(List<String> roles) {
        return SecurityUtils.getSubject().hasAllRoles(roles);
    }

    public static boolean hasRoles(String...roles) {
        boolean hasRoles = false;
        if (ObjectUtils.isNotNull(roles)) {
            hasRoles = hasRoles(Arrays.asList(roles));
        }
        return hasRoles;
    }

    /**
     *  多个角色中，是否有一个
     * @param roles
     * @return boolean
     */
    public static boolean hasAnyRoles(List<String> roles) {
        if (ObjectUtils.isNotEmpty(roles)) {
            for (String role : roles) {
                if (hasRole(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasAnyRoles(String...roles) {
        boolean hasAnyRoles = false;
        if (ObjectUtils.isNotNull(roles)) {
            hasAnyRoles = hasAnyRoles(Arrays.asList(roles));
        }
        return hasAnyRoles;
    }

    public static boolean isPermitted(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

    public static boolean isPermittedAll(String...permissions) {
        return SecurityUtils.getSubject().isPermittedAll(permissions);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

}
