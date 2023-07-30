package com.example.empinfosystem.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.empinfosystem.pojo.Result;
import com.example.empinfosystem.utils.JwtUtils;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String curUrl = request.getRequestURL().toString();
        log.info("req url:{}", curUrl);
        if (curUrl.contains("login")){
            log.info("登录操作，放行。。。");
            filterChain.doFilter(request, response);
            return;
        }

        //获取令牌
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)){
            log.error("token is null!!!");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }

        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            response.getWriter().write(jsonString);
            return;
        }

        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
    }
}
