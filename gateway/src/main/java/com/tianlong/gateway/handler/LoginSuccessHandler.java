package com.tianlong.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.tianlong.common.constant.ResultCode;
import com.tianlong.common.model.user.RoleInfo;
import com.tianlong.common.util.ResUtil;
import com.tianlong.common.util.ResponseUtil;

import com.tianlong.gateway.model.AuthUserDetails;
import com.tianlong.gateway.service.IRoleInfoService;
import com.tianlong.gateway.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName LoginSuccessHandlerFilter
 * @Description 登陆认证成功处理过滤器
 * @Author zhouwenheng
 * @Version 1.0
 **/

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IRoleInfoService roleInfoService;
    /**
     * @Author zhouwenheng
     * @Description 用户认证成功后 生成token并返回
     * @Param [request, response, authentication]
     * @return void
     **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthUserDetails authUserDetails=(AuthUserDetails)authentication.getPrincipal();//从内存中获取当前认证用户信息
        String Username=authentication.getName();

        //在redis中查询用户之前是否登入
        String oldToken=redisTemplate.opsForValue().get("token_"+Username);
        if(!StringUtils.isBlank(oldToken)){
            //清除旧Token
            redisTemplate.delete("token_"+Username);
        }

        String roleInfosMapPermission=redisTemplate.opsForValue().get("authentication:roleinfos:permissions");
        if(StringUtils.isBlank(roleInfosMapPermission)){
            //将角色与权限关系存入redis
            List<RoleInfo> roleInfos=roleInfoService.findRoleInfoAndPermission();
            redisTemplate.opsForValue().set("authentication:roleinfos:permissions", JSON.toJSONString(roleInfos),480,TimeUnit.MINUTES);
        }

        //创建token
        String accessToken = tokenUtil.createAccessJwtToken(authUserDetails);

        //存入redis
        redisTemplate.opsForValue().set("token_"+Username,accessToken,480,TimeUnit.MINUTES);
        HashMap<String,String> map=new HashMap<>();
        map.put("accessToken",accessToken);
        ResponseUtil.out(response, ResUtil.getJsonStr(ResultCode.OK,"登录成功",map));
    }
}