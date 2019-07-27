package com.nazar.controller.routes;

public interface PageRoutes {
    String REDIRECT = "redirect:";
    String MAIN = "/main";
    String USER_PAGE = "/userpage";
    String LOGIN = "/login";
    String REGISTRATION = "/registration";
    String ACCESS_DENIED = "/accessdenied";
    String NEW_MEAL = USER_PAGE + "/newmeal";
    String ADD_FOOD = USER_PAGE + "/addfood";
    String ADD_FOOD_TO_MEAL = NEW_MEAL + "/addfoodtomeal";
    String ALL_MEALS = USER_PAGE + "/allmeals";
    String DELETE_FOOD_FROM_MEAL = NEW_MEAL + "/deletefoodfrommeal";
    String SAVE_MEAL = NEW_MEAL + "/savemeal";
    String SAVE_FOOD = ADD_FOOD + "/savefood";
    String ADMIN = "/admin";
    String MAKE_FOOD_PUBLIC = ADMIN + "/makefoodpublic";

}
