package com.example.empinfosystem.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("abc拦截了请求");
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("已经放行");
    }
}
