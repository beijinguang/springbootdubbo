package com.idea4j.filter;

import com.alibaba.dubbo.rpc.RpcContext;
import com.idea4j.common.shardb.DatabaseContants;
import com.idea4j.common.shardb.DatabaseContextHolder;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by markee on 2017/1/4.
 */
public class TokenFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (DatabaseContants.DB_KEY.equals(name)) {
                    String value = cookie.getValue();
                    DatabaseContextHolder.setDatabaseKey(value);
                    if (RpcContext.getContext() != null) {
                        RpcContext.getContext().setAttachment(
                                DatabaseContants.DB_KEY, value);
                    }

                }
            }
        }

        String url = request.getRequestURI();

        try {
            filterChain.doFilter(request, response);
        } finally {
            DatabaseContextHolder.removeDatabaseKey();
            RpcContext.getContext().removeAttachment(DatabaseContants.DB_KEY);
        }
    }

    public void destroy() {

    }
}
