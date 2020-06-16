package com.tianlong.gateway.security.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlong.gateway.security.entity.Users;
import com.tianlong.gateway.security.mapper.UsersMapper;
import com.tianlong.gateway.security.model.AuthUserModel;
import com.tianlong.gateway.security.service.IUsersService;
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