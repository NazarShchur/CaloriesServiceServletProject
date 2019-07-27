package com.nazar.service;
import com.nazar.config.SecurityConfig;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


public class SecurityService {
    public static boolean hasPermission(HttpServletRequest request, User user){
        System.out.println(request.getPathInfo());
        return user.getRoles().stream()
                .anyMatch(r -> SecurityConfig.getSecuredPagesForRole(r).contains(request.getPathInfo()));
    }
}
