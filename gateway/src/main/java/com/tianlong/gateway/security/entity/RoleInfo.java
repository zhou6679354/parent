package com.tianlong.gateway.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName RoleInfo
 * @Description  角色信息
 * @Author zhouwenheng
 * @Date 2020/6/16 14:26
 * @Version 1.0
 **/
public class RoleInfo implements Serializable {
    private static final long serialVersionUID = 1L;



    /**
     * 角色id
     */
    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 角色等级  从小到大  1大于2
     */
    private Integer roleLevel;
    /**
     * 启用'ENABLE'/关闭'DISABLE'
     */
    private String status;
    /**
     * 角色说明
     */
    private String description;
    /**
     * 角色权限
     */
    @TableField(exist = false)
    private List<PermissionInfo> permissionInfos;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PermissionInfo> getPermissionInfos() {
        return permissionInfos;
    }

    public void setPermissionInfos(List<PermissionInfo> permissionInfos) {
        this.permissionInfos = permissionInfos;
    }
}
