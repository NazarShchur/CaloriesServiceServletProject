package com.nazar.controller.filter;


import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.entity.User;
import com.nazar.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


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
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user got from session " + user);
        String PATHINFO = Optional.ofNullable(req.getPathInfo()).orElse("");
        if(SecurityService.isUserLogged(req)
                &&(PATHINFO.equals(PageRoutes.LOGIN) || PATHINFO.equals(PageRoutes.REGISTRATION))){
            System.out.println("Redirecting to " + PageRoutes.USERPAGE);
            resp.sendRedirect(req.getServletPath() + PageRoutes.USERPAGE);
            return;
        }
        if (SecurityService.isPageSecured(req)){
            if (user != null) {
                if (SecurityService.hasPermisson(req, user)) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("Redirecting to " + PageRoutes.ACCESSDENIED);
                    resp.sendRedirect(req.getServletPath() + PageRoutes.ACCESSDENIED);
                }
            } else {
                System.out.println("Redirecting to " + PageRoutes.LOGIN);
                resp.sendRedirect(req.getServletPath() + PageRoutes.LOGIN);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
