package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.entity.Meal;

import java.util.List;

public class MealService {
    private FoodService foodService = new FoodService();
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveNewMeal(Meal meal){
        try(MealDao dao = daoFactory.createMealDao()) {
            dao.create(meal);
        }
    }
    public void countAllCalories(Meal meal){
        meal.setAllCalories(meal.getFoodMap().keySet().stream()
                .mapToInt(a->(int)((a.getCarbohydrate()*4
                        + a.getFats()*9
                        + a.getProtein()*4)
                        * meal.getFoodMap().get(a)))
                .sum());
    }
    public List<Meal> findMealsByUserId(int id){
        List<Meal> mealList;
        try(MealDao dao = daoFactory.createMealDao()){
            mealList = dao.findAllByUserId(id);
            mealList.forEach(a->a.setFoodMap(dao.findFoodByMealId(a.getId())));
        }
       return mealList;
    }
}
