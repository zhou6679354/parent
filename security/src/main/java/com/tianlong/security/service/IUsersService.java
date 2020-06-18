package com.tianlong.security.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.tianlong.common.model.user.AuthUserModel;
import com.tianlong.common.model.user.Users;
import org.springframework.stereotype.Service;

/**
 *  服务类接口
 * @author zhouwenheng
 * @since 2020-06-16
 */
@Service
public interface IUsersService extends IService<Users> {
    /**
     * @return AuthUserModel
     * @Author zhouwenheng
     * @Description 通过用户名 查询用户信息 角色列表 权限列表
     * @Param [username]
     **/
    AuthUserModel findAuthUserByUsername(String username);
}
