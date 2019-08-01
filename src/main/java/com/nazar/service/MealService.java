package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Meal;
import jdk.nashorn.internal.runtime.logging.Loggable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MealService {
    private final UserService userService = new UserService();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private final static Logger logger = Logger.getLogger(MealService.class);
    public void saveNewMeal(Meal meal){
        try(MealDao dao = daoFactory.createMealDao()) {
            dao.create(meal);
        }
        logger.debug("Saved new meal " + meal);
    }
    public void countAllCalories(Meal meal){
        meal.setAllCalories(getAllCalories(meal));
    }
    private int getAllCalories(Meal meal){
        return meal.getFoodMap().keySet().stream()
                .mapToInt(a->(int)((a.getCarbohydrate()*4
                        + a.getFats()*9
                        + a.getProtein()*4)
                        * meal.getFoodMap().get(a)))
                .sum();
    }
    public List<Meal> findMealsByUserId(int id){
        try(MealDao dao = daoFactory.createMealDao()){
            return dao.findAllByUserId(id);
        }

    }
    private List<Meal> getTodayMeals(List<Meal> meals){
        return meals.stream()
                .filter(a->a.getAddTime().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
    }

    private int getAllCalories(List<Meal> meals){
        return meals.stream()
                .mapToInt(this::getAllCalories)
                .sum();
    }
    public int getAllCaloriesFromCurrentUserTodayMeals(HttpServletRequest request){
        return getAllCalories(getTodayMeals(findMealsByUserId(userService.getCurrentUser(request).getId())));
    }



}
