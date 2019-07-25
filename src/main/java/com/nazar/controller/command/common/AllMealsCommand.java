package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.model.entity.Meal;
import com.nazar.service.FoodService;
import com.nazar.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllMealsCommand implements Command {
    private final String USERID = "userid";
    private final String ALLMEALS = "allmeals";

    private MealService mealService;

    public AllMealsCommand(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentUserID = (int)request.getSession().getAttribute(USERID);
        List<Meal> mealList = mealService.findMealsByUserId(currentUserID);
        mealList.forEach(mealService::countAllCalories);
        request.getSession().setAttribute(ALLMEALS, mealList);
        return JSPRoutes.ALLMEALS;
    }
}
