package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;


public class SaveFoodCommand implements Command {
    private final String CARBOHYDRATE = "carbohydrate";
    private final String FOODNAME = "foodName";
    private final String FATS = "fats";
    private final String PROTEIN = "protein";
    private final String USER_ID = "userid";
    private FoodService foodService;

    public SaveFoodCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String execute(HttpServletRequest request)
    {//todo validation
        foodService.savePrivate(PrivateFood.builder()
                .userID((int)request.getSession().getAttribute(USER_ID))
                .carbohydrate(Double.parseDouble(request.getParameter(CARBOHYDRATE)))
                .name(request.getParameter(FOODNAME))
                .protein(Double.parseDouble(request.getParameter(PROTEIN)))
                .fats(Double.parseDouble(request.getParameter(FATS)))
                .build());
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.ADD_FOOD;
    }
}
