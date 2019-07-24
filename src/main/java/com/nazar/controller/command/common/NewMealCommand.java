package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.JSPRoutes;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.User;
import com.nazar.service.FoodService;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class NewMealCommand implements Command {
    private final String FOODSET = "foodSet";
    private final String FOODSETSIZE = "foodSetSize";
    private FoodService foodService;

    public NewMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){
        Set<Food> foodSet = new HashSet<>();
        foodSet.addAll(foodService.getFoodListByIsPublic(true));
        foodSet.addAll(foodService.getFoodListByUserID((int)request.getSession().getAttribute("userid")));
        request.setAttribute(FOODSETSIZE,foodSet.size() - 1 );
        request.setAttribute(FOODSET,foodSet);
        return JSPRoutes.NEWMEAL;
    }
}
