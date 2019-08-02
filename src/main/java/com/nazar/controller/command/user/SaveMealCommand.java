package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Meal;
import com.nazar.model.entity.User;
import com.nazar.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

public class SaveMealCommand implements Command {
    private final String CURRENT_MAP = "currentMap";
    private final String DESCRIPTION = "description";
    private final String USER = "user";
    private final String NO_FOOD_ADDED = "?noFoodAdded=true";
    private MealService mealService;

    public SaveMealCommand(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        FoodCountMapDTO currentMap = Optional.ofNullable((FoodCountMapDTO)request.getSession().getAttribute(CURRENT_MAP))
                .orElse(new FoodCountMapDTO());
        String description = request.getParameter(DESCRIPTION);
        User currentUser = (User)request.getSession().getAttribute(USER);
        if (currentMap.getMap().size() == 0){
            return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.NEW_MEAL + NO_FOOD_ADDED;
        }
            mealService.saveNewMeal(Meal.builder()
                    .description(description)
                    .foodMap(currentMap.getMap())
                    .userId(currentUser.getId())
                    .build());
        request.getSession().setAttribute(CURRENT_MAP, null);
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.USER_PAGE;
    }
}
