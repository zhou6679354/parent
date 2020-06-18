package com.tianlong.security.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.tianlong.common.model.user.PermissionInfo;
import com.tianlong.security.mapper.PermissionInfoMapper;
import com.tianlong.security.service.IPermissionInfoService;
import org.springframework.stereotype.Service;


/**
 *  服务实现类
 * @author zhouwenheng
 * @since 2020-06-16
 */
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo> implements IPermissionInfoService {

}
