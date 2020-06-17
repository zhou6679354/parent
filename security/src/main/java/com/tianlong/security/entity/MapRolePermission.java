package com.tianlong.security.entity;

import java.io.Serializable;

/**
 * @ClassName MapRolePermission
 * @Description  权限与角色映射
 * @Author zhouwenheng
 * @Date 2020/6/16 14:26
 * @Version 1.0
 **/
public class MapRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色与权限关系id
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String permissionId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }



    @Override
    public String toString() {
        return "MapRolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                "}";
    }
}
