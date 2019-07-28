package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.model.entity.Meal;
import com.nazar.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AllMealsCommand implements Command{

    private final String USER_ID = "userid";
    private final String MEALS_PER_PAGE_PARAM ="mealsPerPage";
    private final String MEALS_LIST = "mealsList";
    private final String PAGE_COUNT = "pageCount";
    private final String PAGE_PARAM = "page";
    private final int MEALS_PER_PAGE = 3;

    private MealService mealService;

    public AllMealsCommand(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentUserID = (int)request.getSession().getAttribute(USER_ID);
        List<Meal> mealList = mealService.findMealsByUserId(currentUserID);
        int allMealsCount = mealList.size();
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter(PAGE_PARAM)).orElse("1"));
        int fromIndex = (page * MEALS_PER_PAGE) - MEALS_PER_PAGE;
        int toIndex = fromIndex + MEALS_PER_PAGE > allMealsCount
                ? allMealsCount
                : fromIndex + MEALS_PER_PAGE;
        List<Meal> pageMealList = mealList.subList(fromIndex, toIndex);
        pageMealList.forEach(mealService::countAllCalories);
        request.setAttribute(MEALS_LIST, pageMealList);
        request.setAttribute(MEALS_PER_PAGE_PARAM, MEALS_PER_PAGE);
        int pageCount = allMealsCount%MEALS_PER_PAGE == 0
                ? allMealsCount/MEALS_PER_PAGE
                : allMealsCount/MEALS_PER_PAGE + 1;
        request.setAttribute(PAGE_COUNT, pageCount);
        return JSPRoutes.ALL_MEALS;
    }
}
