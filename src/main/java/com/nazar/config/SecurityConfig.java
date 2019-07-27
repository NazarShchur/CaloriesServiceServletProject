package com.nazar.config;

import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.entity.Role;

import java.util.*;

public class SecurityConfig {
    private static Map <Role, List<String>> securedPages = new HashMap<>();
    static {
        init();
    }
    private static void init(){
        securedPages.put(Role.GUEST, Arrays.asList(
                PageRoutes.MAIN,
                PageRoutes.LOGIN,
                PageRoutes.REGISTRATION,
                PageRoutes.AUTH
                ));
        securedPages.put(Role.USER, Arrays.asList(
                PageRoutes.MAIN,
                PageRoutes.USER_PAGE,
                PageRoutes.NEW_MEAL,
                PageRoutes.ADD_FOOD,
                PageRoutes.ADD_FOOD_TO_MEAL,
                PageRoutes.ALL_MEALS,
                PageRoutes.DELETE_FOOD_FROM_MEAL,
                PageRoutes.SAVE_MEAL,
                PageRoutes.SAVE_FOOD,
                PageRoutes.LOGOUT));

        securedPages.put(Role.ADMIN, Arrays.asList(
                PageRoutes.ADMIN,
                PageRoutes.MAKE_FOOD_PUBLIC
        ));
    }

    public static List<String> getSecuredPagesForRole(Role role){
        System.out.println(securedPages.get(role));
        return securedPages.get(role);
    }

}
