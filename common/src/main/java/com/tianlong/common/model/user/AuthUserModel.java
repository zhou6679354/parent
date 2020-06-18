package com.tianlong.common.model.user;



import java.io.Serializable;
import java.util.List;

/**
 * @ClassName AuthUserModel
 * @Description  登陆用户实列
 * @Author zhouwenheng
 * @Date 2020/6/16 14:26
 * @Version 1.0
 **/
public class AuthUserModel implements Serializable {
    private String username;
    private String password;
    private String status;

    private List<RoleInfo> roleInfos;

    private List<PermissionInfo> permissionInfos;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

    public List<PermissionInfo> getPermissionInfos() {
        return permissionInfos;
    }

    public void setPermissionInfos(List<PermissionInfo> permissionInfos) {
        this.permissionInfos = permissionInfos;
    }
}
