package com.tianlong.security.entity;

import java.io.Serializable;

/**
 * @ClassName Users
 * @Description 用户信息
 * @Author zhouwenheng
 * @Date 2020/6/16 14:26
 * @Version 1.0
 **/
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 用户主键
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     * 手机号
     */
    private Integer mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 是否生效
     */
    private String status;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
