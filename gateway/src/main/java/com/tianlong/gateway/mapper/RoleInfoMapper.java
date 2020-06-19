package com.tianlong.gateway.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianlong.common.model.user.RoleInfo;

import java.util.List;

/**
 *  Mapper 角色信息接口
 * @author security
 * @since 2019-05-06
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    /**
     * @Author zhouwenheng
     * @Description 查询全部角色及对应权限
     * @Param
     * @return
     **/
    List<RoleInfo> findRoleInfoAndPermission();

}