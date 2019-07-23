package com.nazar.service;
import com.nazar.config.SecurityConfig;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


public class SecurityService {
    public static boolean isPageSecured(HttpServletRequest request){
        return Arrays.stream(Role.values())
                .filter(r->SecurityConfig.getSecuredPagesForRole(r).contains(request.getPathInfo()))
                .toArray().length > 0;
    }

    public static Role requiredRole(HttpServletRequest request){
        return  Arrays.stream(Role.values())
                .filter(r->SecurityConfig.getSecuredPagesForRole(r).contains(request.getPathInfo()))
                .findFirst()
                .orElseThrow(RuntimeException::new);//todo custom exception
    }
    public static boolean hasPermisson(HttpServletRequest request, User user){
        return user.getRoles().contains(requiredRole(request));
    }
    public static boolean isUserLogged(HttpServletRequest request){
        return request.getSession().getAttribute("user")!=null;
    }

}