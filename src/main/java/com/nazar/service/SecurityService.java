package com.nazar.service;
import com.nazar.config.SecurityConfig;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;


public class SecurityService {
    public static boolean hasPermission(HttpServletRequest request, User user){
        return user.getRoles().stream()
                .anyMatch(r -> SecurityConfig.getSecuredPagesForRole(r)
                        .contains(Optional.ofNullable(request.getPathInfo()).orElse(PageRoutes.MAIN)));
    }
}
