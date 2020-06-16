package com.tianlong.gateway.security.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.tianlong.gateway.security.entity.PermissionInfo;
import com.tianlong.gateway.security.mapper.PermissionInfoMapper;
import com.tianlong.gateway.security.service.IPermissionInfoService;
import org.springframework.stereotype.Service;



/**
 *  服务实现类
 * @author zhouwenheng
 * @since 2020-06-16
 */
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo> implements IPermissionInfoService {

}
