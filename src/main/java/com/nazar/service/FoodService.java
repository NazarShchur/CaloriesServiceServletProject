package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.Meal;

import java.util.*;
import java.util.stream.Collectors;


public class FoodService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Food> getAllFoodList() {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findAll();
        }
    }

    public List<Food> getFoodListByIsPublic(boolean isPublic) {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findByIsPublic(isPublic);
        }
    }

    public List<Food> getPrivateFoodListByUserID(int id) {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findByUserID(id);
        }
    }

    public List<Food> getFoodListForUser(int id) {
        List<Food> foodList = new ArrayList<>();
        foodList.addAll(getPrivateFoodListByUserID(id));
        foodList.addAll(getFoodListByIsPublic(true));
        return foodList.stream()
                .distinct()
                .sorted(Comparator.comparing(Food::getName))
                .collect(Collectors.toList());
    }

    public List<Food> getAvailableFoodList(Map<Food, Integer> map, int id) {
        return getFoodListForUser(id).stream()
                .filter(f -> !map.keySet().contains(f))
                .collect(Collectors.toList());
    }

    public Food findByID(int id) {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findById(id);
        }
    }
}
