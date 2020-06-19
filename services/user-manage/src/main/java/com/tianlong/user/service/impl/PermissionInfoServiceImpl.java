package com.tianlong.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlong.common.model.user.PermissionInfo;

import com.tianlong.user.mapper.PermissionInfoMapper;
import com.tianlong.user.mapper.RoleInfoMapper;
import com.tianlong.user.service.IPermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *  服务实现类
 * @author zhouwenheng
 * @since 2020-06-16
 */
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo> implements IPermissionInfoService {
    @Autowired
    private PermissionInfoMapper permissionInfoMapper;
    @Override
    public List<PermissionInfo> findPermissionInfo() {
        return permissionInfoMapper.findPermissionInfo();
    }
}
