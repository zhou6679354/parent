package com.tianlong.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianlong.common.model.user.RoleInfo;

import java.util.List;

/**
 *  服务类接口
 * @author zhouwenheng
 * @since 2020-06-16
 */
public interface IRoleInfoService extends IService<RoleInfo> {

    public List<RoleInfo> findRoleInfoAndPermission();

}