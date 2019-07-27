package com.nazar.controller.filter;


import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;


public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) Optional.ofNullable(session.getAttribute("user"))
                .orElse(User.builder()
                        .role(new HashSet<>(Arrays.asList(Role.GUEST)))
                        .build());

        if (SecurityService.hasPermission(req, user)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getServletPath() + PageRoutes.MAIN);
        }
    }

    @Override
    public void destroy() {

    }
}
