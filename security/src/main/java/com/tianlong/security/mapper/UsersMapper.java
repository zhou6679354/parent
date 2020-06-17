package com.tianlong.security.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianlong.gateway.security.entity.Users;
import com.tianlong.gateway.security.model.AuthUserModel;
import org.apache.ibatis.annotations.Param;


/**
 *  Mapper 用户信息接口
 * @author security
 * @since 2019-05-06
 */
public interface UsersMapper extends BaseMapper<Users> {



    /**
     * @Author liuheming
     * @Description 通过用户名 查询用户信息 角色列表 权限列表
     * @Param [username]
     * @return AuthUserModel
     **/
    AuthUserModel findAuthUserByUsername(@Param("username") String username);

}
