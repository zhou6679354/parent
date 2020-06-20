package com.tianlong.gateway.service;

import com.tianlong.common.model.user.AuthUserModel;
import com.tianlong.common.model.user.PermissionInfo;
import com.tianlong.common.model.user.RoleInfo;
import com.tianlong.common.model.user.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  服务类接口
 * @author zhouwenheng
 * @since 2020-06-16
 */
@FeignClient( value = "user-manage" )
public interface IUsersService {
    /**
     * @return AuthUserModel
     * @Author zhouwenheng
     * @Description 通过用户名 查询用户信息 角色列表 权限列表
     * @Param [username]
     **/
    @GetMapping("/findAuthUserByUsername")
    AuthUserModel findAuthUserByUsername(@RequestParam("username")String username);
    /**
     * @return AuthUserModel
     * @Author zhouwenheng
     * @Description 查询全局角色列表 权限列表
     * @Param [username]
     **/
    @GetMapping("/findRoleInfoAndPermission")
    public List<RoleInfo> findRoleInfoAndPermission();
    /**
     * @return AuthUserModel
     * @Author zhouwenheng
     * @Description 查询全局权限列表
     * @Param [username]
     **/
    @GetMapping("/findPermissionInfo")
    List<PermissionInfo> findPermissionInfo();
}
