package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class AddFoodToMealCommand implements Command {

    private FoodService foodService;


    public AddFoodToMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){
        try {
            foodService.addFoodToCart(request);
        } catch (Exception e){
            return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
        }

        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
    }
}
