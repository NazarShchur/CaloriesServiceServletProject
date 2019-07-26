package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.model.entity.Meal;
import com.nazar.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllMealsCommand implements Command{

    private final String USER_ID = "userid";
    private final String ALL_MEALS = "allmeals";

    private MealService mealService;

    public AllMealsCommand(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentUserID = (int)request.getSession().getAttribute(USER_ID);
        List<Meal> mealList = mealService.findMealsByUserId(currentUserID);
        mealList.forEach(mealService::countAllCalories);
        request.getSession().setAttribute(ALL_MEALS, mealList);
        return JSPRoutes.ALL_MEALS;
    }
}
