package com.nazar.controller.filter;


import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.utility.SecurityUtility;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(SecurityUtility.isPageSecured(req)){
            resp.sendRedirect(req.getServletPath() + PageRoutes.LOGIN);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
