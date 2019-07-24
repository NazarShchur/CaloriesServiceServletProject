package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class AddFoodToMealCommand implements Command {
    private final String FOODID = "foodID";
    private final String CURRENTMAP = "currentMap";
    private final String COUNT = "count";
    private final String ISFOODINMAP = "isFoodInMap";
    private FoodService foodService;


    public AddFoodToMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){

        Food addedFood = foodService.findByID(Integer.parseInt(request.getParameter(FOODID)));
        int countOfAddedFood = Integer.parseInt(request.getParameter(COUNT));
        FoodCountMapDTO currentMap = Optional.ofNullable((FoodCountMapDTO)request.getSession().getAttribute(CURRENTMAP))
                .orElse(new FoodCountMapDTO());
        currentMap.getMap().put(addedFood, countOfAddedFood);
        request.getSession().setAttribute(CURRENTMAP, currentMap);
        request.getSession().setAttribute(ISFOODINMAP, true);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEWMEAL;
    }
}
