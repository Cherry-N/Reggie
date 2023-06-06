package com.longxingyu.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.longxingyu.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@code @Create:}  2023-06-06  14 : 04
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */
@SuppressWarnings({"all"})
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException 检查用户是否已经完成登陆
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //1获取本次请求的URL
        String requestURI = httpServletRequest.getRequestURI();
        log.info("拦截到本次请求：{}", requestURI);

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "front/**"
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3.如果不需要处理，直接放行
        if (check) {
            log.info("本次请求：{},不需要处理", requestURI);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //4.判断登陆状态，如果已登陆，则直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null) {
            log.info("用户已登录，用户id为{}", httpServletRequest.getSession().getAttribute("employee"));
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //5.如果未登录，则返回未登录结果,通过输出流方式向页面响应数据
        log.info("用户未登录");
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
