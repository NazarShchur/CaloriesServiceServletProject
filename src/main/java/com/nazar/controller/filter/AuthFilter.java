package com.nazar.controller.filter;


import com.nazar.controller.routes.PageRoutes;
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
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String PATHINFO = Optional.ofNullable(req.getPathInfo()).orElse("");
        if (SecurityService.isUserLogged(req)
                && (PATHINFO.equals(PageRoutes.LOGIN) || PATHINFO.equals(PageRoutes.REGISTRATION))) {
            System.out.println("Redirecting to " + PageRoutes.USER_PAGE);
            resp.sendRedirect(req.getServletPath() + PageRoutes.USER_PAGE);
            return;
        }
        if (SecurityService.isPageSecured(req)) {
            if (user != null) {//todo optional
                if (SecurityService.hasPermission(req, user)) {
                    chain.doFilter(request, response);
                } else {
                    System.out.println("Redirecting to " + PageRoutes.ACCESS_DENIED);//todo remove sout
                    resp.sendRedirect(req.getServletPath() + PageRoutes.ACCESS_DENIED);
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
