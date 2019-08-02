package com.nazar.controller.command.user;

import com.nazar.controller.command.Command;
import com.nazar.controller.routes.JSPRoutes;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.service.MealService;
import com.nazar.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserPageCommand implements Command {
    private final String TODAY_EATEN = "todayEaten";
    private final String IS_ADMIN = "isAdmin";
    private final String IS_NORM_EXCEEDED = "isNormExceeded";
    private UserService userService;
    private MealService mealService;

    public UserPageCommand(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @Override
    public String execute(HttpServletRequest request){
        User currentUser = userService.getCurrentUser(request);
        userService.countDailyCalories(currentUser);
        request.getSession().setAttribute(TODAY_EATEN, mealService.getAllCaloriesFromCurrentUserTodayMeals(request));
        request.getSession().setAttribute(IS_ADMIN, currentUser.getRoles().contains(Role.ADMIN));
        request.getSession().setAttribute(IS_NORM_EXCEEDED,
                mealService.getAllCaloriesFromCurrentUserTodayMeals(request) > currentUser.getDailyCalories());
        return JSPRoutes.USER_PAGE;
    }
}
