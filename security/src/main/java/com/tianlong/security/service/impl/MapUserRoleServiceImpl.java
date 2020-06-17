package com.tianlong.security.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianlong.gateway.security.entity.MapUserRole;
import com.tianlong.gateway.security.mapper.MapUserRoleMapper;
import com.tianlong.gateway.security.service.IMapUserRoleService;
import org.springframework.stereotype.Service;


/**
 *  服务实现类
 * @author zhouwenheng
 * @since 2020-06-16
 */
@Service
public class MapUserRoleServiceImpl extends ServiceImpl<MapUserRoleMapper, MapUserRole> implements IMapUserRoleService {

}
