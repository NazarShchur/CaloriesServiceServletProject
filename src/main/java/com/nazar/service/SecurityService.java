package com.nazar.service;
import com.nazar.config.SecurityConfig;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


public class SecurityService {
    public static boolean isPageSecured(HttpServletRequest request){
        System.out.println("PATH IS HERE " + request.getPathInfo());
        return Arrays.stream(Role.values())
                .anyMatch(r->SecurityConfig
                        .getSecuredPagesForRole(r)
                        .contains(request.getPathInfo()));//todo check with String::startsWith
    }

    public static Role requiredRole(HttpServletRequest request){
        return  Arrays.stream(Role.values())
                .filter(r->SecurityConfig.getSecuredPagesForRole(r).contains(request.getPathInfo()))
                .findFirst()
                .orElseThrow(RuntimeException::new);//todo ??
    }
    public static boolean hasPermission(HttpServletRequest request, User user){
        return user.getRoles().contains(requiredRole(request));
    }
    public static boolean isUserLogged(HttpServletRequest request){
        return request.getSession().getAttribute("user")!=null;
    }

}
