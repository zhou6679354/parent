package com.tianlong.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianlong.common.model.user.PermissionInfo;

import java.util.List;


/**
 *  Mapper 权限信息接口
 * @author security
 * @since 2019-05-06
 */
public interface PermissionInfoMapper extends BaseMapper<PermissionInfo> {
    public List<PermissionInfo> findPermissionInfo();
}