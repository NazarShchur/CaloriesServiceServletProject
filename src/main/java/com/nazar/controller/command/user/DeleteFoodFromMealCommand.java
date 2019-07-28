package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;

public class DeleteFoodFromMealCommand implements Command {
    private final String CURRENT_MAP = "currentMap";
    private final String FOOD_ID_TO_DELETE = "foodIDToDelete";

    private FoodService foodService;

    public DeleteFoodFromMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){
        FoodCountMapDTO currentMap = (FoodCountMapDTO)request.getSession().getAttribute(CURRENT_MAP);
        Food foodToDeleteFromMeal = foodService.findByID(Integer.parseInt(request.getParameter(FOOD_ID_TO_DELETE)));
        currentMap.getMap().remove(foodToDeleteFromMeal);
        foodService.countAllCalories(currentMap);
        request.getSession().setAttribute(CURRENT_MAP, currentMap);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
    }
}
