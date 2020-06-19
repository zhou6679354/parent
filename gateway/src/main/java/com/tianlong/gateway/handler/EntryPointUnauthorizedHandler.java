package com.tianlong.gateway.handler;


import com.tianlong.common.constant.ResultCode;
import com.tianlong.common.util.ResUtil;
import com.tianlong.common.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @ClassName EntryPointUnauthorizedHandler
 * @Description TODO
 * @Author zhouwenheng
 * @Version 1.0
 **/
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, ResUtil.getJsonStr(ResultCode.FORBIDDEN, "请登录后操作"));

    }

}