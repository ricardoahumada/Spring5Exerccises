package com.bananaapps.bananamusic.filter;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

//@Component // with @ServletComponentScan not necessary
@WebFilter(urlPatterns = {"/user"})
public class UserFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        log.info("doFilter Host:" + request.getRemoteHost());
        log.info("Remote Address:" + request.getRemoteAddr());
//        filterchain.doFilter(request, response);
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setStatus(403);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}
