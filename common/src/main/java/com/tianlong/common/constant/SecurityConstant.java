package com.tianlong.common.constant;
/**
 * @ClassName SecurityConstant
 * @Description 安全参数常量
 * @Author zhouwenheng
 * @Date 2020/6/16 15:18
 * @Version 1.0
 **/
public class SecurityConstant {
    /**
     * token参数头
     */
    public static String HEADER = "Authorization";



    /**
     * token分割
     */
    public static String TOKEN_SPLIT = "Bearer ";



    /**
     * token中自定义权限标识
     */
    public static String AUTHORITIES = "authorities";

    /**
     * token失效时间
     */
    public static Integer tokenExpirationTime = 360;



    /**
     * Token 发行人
     */
    public static String tokenIssuer = "zwh";



    /**
     * JWT签名加密key
     */
    public static String tokenSigningKey = "zhouwenheng";



    /**
     * 刷新Token时间
     */
    public static Integer refreshTokenExpTime = 720;
}
