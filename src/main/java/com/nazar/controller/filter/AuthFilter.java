package com.nazar.controller.filter;


import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.service.SecurityService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;


public class AuthFilter implements Filter {
    private final static Logger logger = Logger.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) Optional.ofNullable(session.getAttribute("user"))
                .orElse(User.builder()
                        .role(new HashSet<>(Arrays.asList(Role.GUEST)))
                        .build());
        logger.debug("User got from session " + user);
        if (SecurityService.hasPermission(req, user)) {
            logger.debug("User " + user.getId() + "has permission");
            chain.doFilter(request, response);
        } else {
            logger.debug("User " + user.getId() + "does not have permission");
            resp.sendRedirect(req.getServletPath() + PageRoutes.MAIN);
        }
    }

    @Override
    public void destroy() {

    }
}
