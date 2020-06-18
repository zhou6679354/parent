package com.tianlong.security.utils;

import com.alibaba.fastjson.JSON;

import com.tianlong.common.constant.SecurityConstant;
import com.tianlong.common.model.user.RoleInfo;
import com.tianlong.security.model.AuthUserDetails;
import com.tianlong.common.enums.Scopes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


/**
 * @ClassName TokenUtil
 * @Description token工具类
 * @Author zhouwenheng
 * @Date 2020/6/16 9:46
 * @Version 1.0
 **/

@Component
public class TokenUtil {

    /**
     * @return AccessJwtToken
     * @Author zhouwenheng
     * @Description 生成新的token方法，并将角色保存
     * @Param [authUserDetails]
     **/
    public String createAccessJwtToken(AuthUserDetails authUserDetails) {
        if (StringUtils.isBlank(authUserDetails.getUsername())) {
            throw new IllegalArgumentException("用户名为空无法创建token");
        }
        Claims claims = Jwts.claims().setSubject(authUserDetails.getUsername());
        //存入角色信息
        List<String> list = new ArrayList<>();
        for (RoleInfo roleInfo : authUserDetails.getRoleInfos()) {
            list.add(roleInfo.getId());
        }


        claims.put(SecurityConstant.AUTHORITIES, JSON.toJSONString(list));


        LocalDateTime currentTime = LocalDateTime.now();


        String token = Jwts.builder()

                .setClaims(claims)

                .setIssuer(SecurityConstant.tokenIssuer)

                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))

                .setExpiration(Date.from(currentTime

                        .plusMinutes(SecurityConstant.tokenExpirationTime)

                        .atZone(ZoneId.systemDefault()).toInstant()))

                .signWith(SignatureAlgorithm.HS512, SecurityConstant.tokenSigningKey)

                .compact();


        return token;

    }


    /**
     * @return JwtToken
     * @Author zhouwenheng
     * @Description 创建刷新token
     * @Param [authUserDetails]
     **/
    public String createRefreshToken(AuthUserDetails authUserDetails) {
        if (StringUtils.isBlank(authUserDetails.getUsername())) {
            throw new IllegalArgumentException("用户名为空无法创建token");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        Claims claims = Jwts.claims().setSubject(authUserDetails.getUsername());
        claims.put(SecurityConstant.AUTHORITIES, Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(SecurityConstant.tokenIssuer)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(SecurityConstant.refreshTokenExpTime)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.tokenSigningKey)
                .compact();
        return token;
    }
}
