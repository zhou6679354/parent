package com.tianlong.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlong.common.model.user.RoleInfo;

import com.tianlong.user.mapper.RoleInfoMapper;
import com.tianlong.user.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 * @author zhouwenheng
 * @since 2020-06-16
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {



    @Autowired
    private RoleInfoMapper roleInfoMapper;

    /**
     * @Author zhouwenheng
     * @Description 查询全部角色及对应权限
     * @Param []
     * @return java.util.List<com.lhm.springcloud.security.entity.RoleInfo>
     **/
    @Override
    public List<RoleInfo> findRoleInfoAndPermission() {
        return roleInfoMapper.findRoleInfoAndPermission();
    }

}
