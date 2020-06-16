package com.tianlong.gateway.security.model;

import com.tianlong.gateway.security.constant.UserConstant;
import com.tianlong.gateway.security.entity.PermissionInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName AuthUserDetails
 * @Description  实现自定义角色与权限管理需要对UserDetails进行重写
 * @Author zhouwenheng
 * @Date 2020/6/16 14:26
 * @Version 1.0
 **/
public class AuthUserDetails extends AuthUserModel implements UserDetails {
    private static final long serialVersionUID = 1L;

    public AuthUserDetails(AuthUserModel user) {
        if (user != null) {
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setStatus(user.getStatus());
            this.setRoleInfos(user.getRoleInfos());
            this.setPermissionInfos(user.getPermissionInfos());
        }
    }

    //将角色权限 放入GrantedAuthorit的自定义实现类MyGrantedAuthority中  为权限判定提供数据
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        List<PermissionInfo> permissions = this.getPermissionInfos();
        if (permissions != null) {
            for (PermissionInfo permission : permissions) {
                GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getPath(), permission.getMethod());
                authorityList.add(grantedAuthority);
            }
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }


    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 是否禁用
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return UserConstant.USER_STATUS_NORMAL.equals(this.getStatus()) ? true : false;
    }
}
