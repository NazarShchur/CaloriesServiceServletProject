package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.command.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;

public class DeleteFoodFromMealCommand implements Command {
    private final String CURRENTMAP = "currentMap";
    private final String ISFOODINMAP = "isFoodInMap";
    private final String FOODIDTODELTE = "foodIDToDelete";

    private FoodService foodService;

    public DeleteFoodFromMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){
        System.out.println("TRY TO DELETE NOW");
        FoodCountMapDTO currentMap = (FoodCountMapDTO)request.getSession().getAttribute(CURRENTMAP);
        Food foodToDeleteFromMeal = foodService.findByID(Integer.parseInt(request.getParameter(FOODIDTODELTE)));
        currentMap.getMap().remove(foodToDeleteFromMeal);
        request.getSession().setAttribute(ISFOODINMAP,currentMap.getMap().size() > 0);
        request.getSession().setAttribute(CURRENTMAP, currentMap);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEWMEAL;
    }
}
