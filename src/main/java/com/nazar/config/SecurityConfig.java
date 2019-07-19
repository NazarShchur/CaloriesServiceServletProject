package com.nazar.config;

import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.entity.Role;

import java.util.HashMap;
import java.util.Map;

public class SecurityConfig {
    private static Map securedPages;
    static {
        init();
    }
    private static void init(){
        securedPages = new HashMap<Role, String>();
        securedPages.put(Role.USER, PageRoutes.USERPAGE);
    }
    public static Map getSecuredPages(){
        return securedPages;
    }

}
