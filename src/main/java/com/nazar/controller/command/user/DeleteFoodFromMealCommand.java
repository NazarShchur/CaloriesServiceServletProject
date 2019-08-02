package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;

public class DeleteFoodFromMealCommand implements Command {


    private FoodService foodService;

    public DeleteFoodFromMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){
        foodService.deleteFoodFromCart(request);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
    }
}
