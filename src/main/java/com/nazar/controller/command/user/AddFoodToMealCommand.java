package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class AddFoodToMealCommand implements Command {
    private final static Logger logger = Logger.getLogger(AddFoodToMealCommand.class);

    private FoodService foodService;


    public AddFoodToMealCommand(FoodService foodService){
        this.foodService = foodService;
    }
    @Override
    public String execute(HttpServletRequest request){
        try {
            foodService.addFoodToCart(request);
        } catch (Exception e){
            logger.debug("food have not been added to cart", e);
            return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
        }
        logger.debug("food added to cart");
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL;
    }
}
