package com.nazar.controller.command.admin;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;


public class AdminCommand implements Command {
    private final String USER_FOOD = "userFood";

    private FoodService foodService;

    public AdminCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(USER_FOOD, foodService.findPrivate());
        return JSPRoutes.ADMIN;
    }
}
