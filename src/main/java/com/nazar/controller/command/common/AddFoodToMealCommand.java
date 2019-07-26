package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class AddFoodToMealCommand implements Command {
    private final String FOOD_ID = "foodID";
    private final String CURRENT_MAP = "currentMap";
    private final String COUNT = "count";
    private final String IS_FOOD_IN_MAP = "isFoodInMap";
    private FoodService foodService;


    public AddFoodToMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){

        Food addedFood = foodService.findByID(Integer.parseInt(request.getParameter(FOOD_ID)));
        int countOfAddedFood = Integer.parseInt(request.getParameter(COUNT));
        FoodCountMapDTO currentMap = Optional.ofNullable((FoodCountMapDTO)request.getSession().getAttribute(CURRENT_MAP))
                .orElse(new FoodCountMapDTO());
        currentMap.getMap().put(addedFood, countOfAddedFood);
        foodService.countAllCalories(currentMap);
        request.getSession().setAttribute(CURRENT_MAP, currentMap);
        request.getSession().setAttribute(IS_FOOD_IN_MAP, true);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
    }
}
