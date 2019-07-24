package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.entity.Meal;

public class MealService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveNewMeal(Meal meal){
        try(MealDao dao = daoFactory.createMealDao()) {
            dao.create(meal);
        }
    }
}
