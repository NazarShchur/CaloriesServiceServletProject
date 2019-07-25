package com.nazar.model.dao.interfaces;

import com.nazar.model.entity.Food;
import com.nazar.model.entity.Meal;

import java.util.List;
import java.util.Map;


public interface MealDao extends CRUDDao<Meal> {
    List<Meal> findAllByUserId(int id);
    Map<Food, Integer> findFoodByMealId(int id);
}
