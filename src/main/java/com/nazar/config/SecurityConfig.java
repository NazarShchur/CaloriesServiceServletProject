package com.nazar.config;

import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.entity.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityConfig {
    private static Map <Role, List<String>> securedPages = new HashMap<>();
    static {
        init();
    }
    private static void init(){
        List<String> userPagesList = new ArrayList<>();
        userPagesList.add(PageRoutes.USERPAGE);
        securedPages.put(Role.USER, userPagesList);
        List<String> adminPagesList = new ArrayList<>();
        securedPages.put(Role.ADMIN, adminPagesList);
    }

    public static List<String> getSecuredPagesForRole(Role role){
        return securedPages.get(role);
    }

}
