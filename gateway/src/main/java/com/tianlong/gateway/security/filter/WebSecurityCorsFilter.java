package com.tianlong.gateway.security.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**

 * @ClassName WebSecurityCorsFilter
 * @Description Security过滤器
 * @Author zhouwenheng
 * @Version 1.0
 **/
public class WebSecurityCorsFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        // res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, x-requested-with, Cache-Control");
        chain.doFilter(request, res);

    }

    @Override
    public void destroy() {

    }
}
