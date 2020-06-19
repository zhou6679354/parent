package com.tianlong.gateway.service;

import com.tianlong.common.model.user.RoleInfo;

import java.util.List;

/**
 *  服务类接口
 * @author zhouwenheng
 * @since 2020-06-16
 */
public interface IRoleInfoService  {

    public List<RoleInfo> findRoleInfoAndPermission();

}