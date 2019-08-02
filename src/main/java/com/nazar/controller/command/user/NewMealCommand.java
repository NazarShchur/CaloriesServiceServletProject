package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class NewMealCommand implements Command {
    private final String AVAILABLE_FOOD = "availableFood";
    private final String CURRENT_MAP = "currentMap";
    private final String IS_FOOD_IN_MAP = "isFoodInMap";
    private final String USER_ID = "userid";

    private FoodService foodService;

    public NewMealCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentUserID = (int) request.getSession().getAttribute(USER_ID);
        FoodCountMapDTO currentMap = Optional.ofNullable((FoodCountMapDTO) request.getSession().getAttribute(CURRENT_MAP))
                .orElse(new FoodCountMapDTO());
        List<Food> availableFood = foodService.getAvailableFoodList(currentMap.getMap(), currentUserID);
        request.getSession().setAttribute(AVAILABLE_FOOD, availableFood);
        request.getSession().setAttribute(IS_FOOD_IN_MAP,currentMap.getMap().size() > 0);
        return JSPRoutes.NEW_MEAL;
    }
}
