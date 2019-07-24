package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.PageRoutes;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Meal;
import com.nazar.model.entity.User;
import com.nazar.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class SaveMealCommand implements Command {
    private final String CURRENTMAP = "currentMap";
    private final String DESCRIPTION = "description";
    private final String USER = "user";
    private MealService mealService;

    public SaveMealCommand(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        FoodCountMapDTO currentMap = (FoodCountMapDTO)request.getSession().getAttribute(CURRENTMAP);
        String description = request.getParameter(DESCRIPTION);
        User currentUser = (User)request.getSession().getAttribute(USER);
        mealService.saveNewMeal(Meal.builder()
                    .addTime(LocalDate.now())
                    .description(description)
                    .foodMap(currentMap.getMap())
                    .user(currentUser)
                    .build());
        return PageRoutes.REDIRECT + request.getServletPath() + PageRoutes.USERPAGE;
    }
}
