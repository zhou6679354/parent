package com.tianlong.gateway.security.filter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianlong.gateway.security.constant.ResultCode;
import com.tianlong.gateway.security.constant.SecurityConstant;
import com.tianlong.gateway.security.exception.CommonException;
import com.tianlong.gateway.security.model.MyGrantedAuthority;
import com.tianlong.gateway.security.utils.ResUtil;
import com.tianlong.gateway.security.utils.ResponseUtil;
import com.tianlong.gateway.security.utils.SpringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT过滤器
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private static String FILTER_APPLIED = "__spring_security_JWTAuthenticationFilter_filterApplied";


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //解决同一请求，两次经过过滤器  原因：过滤器被加载WebSecurityConfig和spring都加载了
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, true);
        //获取请求头
        String header = request.getHeader(SecurityConstant.HEADER);
        //如果请求头中不存在 或  格式不对  则进入下个过滤器
        if (StringUtils.isBlank(header) || !header.startsWith(SecurityConstant.TOKEN_SPLIT)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            ResponseUtil.out(response, ResUtil.getJsonStr(ResultCode.BAD_REQUEST, e.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }


    /**
     * @return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * @Author zhouwenheng
     * @Description 对token进行解析认证
     * @Date 16:11 2020/6/16
     * @Param [request, response]
     **/
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws CommonException {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);
        String token = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isNotBlank(token)) {
            // 解析token
            Claims claims = null;
            try {
                claims = Jwts.parser()
                        .setSigningKey(SecurityConstant.tokenSigningKey)
                        .parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
                        .getBody();
            } catch (ExpiredJwtException e) {
                throw new CommonException(ResultCode.BAD_REQUEST, "登录已失效，请重新登录");
            } catch (Exception e) {
                throw new CommonException(ResultCode.BAD_REQUEST, "解析token错误");
            }

            //获取用户名
            String username = claims.getSubject();
            //判定token
            String oldToken = stringRedisTemplate.opsForValue().get("token_" + username);
            if (StringUtils.isBlank(oldToken)) {
                throw new CommonException(ResultCode.BAD_REQUEST, "Token被注销，请重新登录");
            }
            if (!oldToken.equals(token.replace(SecurityConstant.TOKEN_SPLIT, ""))) {
                throw new CommonException(ResultCode.BAD_REQUEST, "Token已失效，请重新登录");
            }
            //获取redis中角色与权限关系
            String roleInfosMapPermission = stringRedisTemplate.opsForValue().get("authentication:roleinfos:permissions");
            if (StringUtils.isBlank(roleInfosMapPermission)) {
                throw new CommonException(ResultCode.BAD_REQUEST, "权限已变更，请重新登录");
            }

            List<MyGrantedAuthority> authorities = new ArrayList<MyGrantedAuthority>();
            //获取当前用户角色
            String authority = claims.get(SecurityConstant.AUTHORITIES).toString();
            if (StringUtils.isNotBlank(authority)) {
                JSONArray list = JSONArray.parseArray(authority);
                JSONArray redisData = JSONArray.parseArray(roleInfosMapPermission);
                //循环查找权限
                for (int i = 0; i < list.size(); i++) {
                    String userRoleId = list.getString(i);//jtw取出的角色id
                    for (int j = 0; j < redisData.size(); j++) {
                        JSONObject redisArray = redisData.getJSONObject(j);
                        if (userRoleId.equals(redisArray.getString("id"))) {//对比
                            JSONArray redisDataPer = redisArray.getJSONArray("permissionInfos");//获取权限
                            for (int z = 0; z < redisDataPer.size(); z++) {
                                JSONObject jsonObject = redisDataPer.getJSONObject(j);
                                //加入权限列表
                                authorities.add(new MyGrantedAuthority(jsonObject.getString("path"), jsonObject.getString("method")));
                            }
                        }
                    }
                }
            } else {
                throw new CommonException(ResultCode.BAD_REQUEST, "用户角色为空");
            }

            if (StringUtils.isNotBlank(username)) {
                //此处password不能为null
                User principal = new User(username, "", authorities);
                return new UsernamePasswordAuthenticationToken(principal, null, authorities);
            }
        }
        return null;
    }
}