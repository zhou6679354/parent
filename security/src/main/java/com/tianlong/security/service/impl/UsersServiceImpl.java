package com.tianlong.security.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.tianlong.common.model.user.AuthUserModel;
import com.tianlong.common.model.user.Users;
import com.tianlong.security.mapper.UsersMapper;
import com.tianlong.security.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 * @author zhouwenheng
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public AuthUserModel findAuthUserByUsername(String username) {
        return usersMapper.findAuthUserByUsername(username);
    }

}