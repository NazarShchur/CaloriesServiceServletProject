package com.nazar.controller.command.common;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.model.entity.User;
import com.nazar.service.MealService;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserPageCommand implements Command {
    private final String USER = "user";
    private final String TODAY_EATEN = "todayEaten";
    private UserService userService;
    private MealService mealService;

    public UserPageCommand(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request){
        userService.countDailyCalories((User)request.getSession().getAttribute(USER));
        request.getSession().setAttribute(TODAY_EATEN, mealService.getAllCaloriesFromCurrentUserTodayMeals(request));
        return JSPRoutes.USER_PAGE;
    }
}
