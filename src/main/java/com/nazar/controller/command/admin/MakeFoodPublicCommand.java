package com.nazar.controller.command.admin;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;

public class MakeFoodPublicCommand implements Command {
    private final String FOOD_ID = "foodID";

    private FoodService foodService;

    public MakeFoodPublicCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int foodIdToUpdate = Integer.parseInt(request.getParameter(FOOD_ID));
        foodService.updateToPublic(foodIdToUpdate);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.ADMIN;
    }
}
