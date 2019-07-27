package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;

import java.util.*;
import java.util.stream.Collectors;


public class FoodService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    public List<PrivateFood> findPrivate(){
        try(FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findPrivate();
        }
    }

    public void updateToPublic(int id){
        try(FoodDao dao = daoFactory.createFoodDao()) {
            dao.updateToPublic(id);
        }
    }
    public List<Food> getPublicFoodList() {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findPublicFood();
        }
    }

    public List<PrivateFood> getPrivateFoodListByUserID(int id) {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findByUserID(id);
        }
    }

    public List<Food> getFoodListForUser(int id) {
        List<Food> foodList = new ArrayList<>();
        foodList.addAll(getPrivateFoodListByUserID(id));
        foodList.addAll(getPublicFoodList());
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
    public void save(Food food){
        try (FoodDao dao = daoFactory.createFoodDao()){
            dao.create(food);
        }
    }
    public void savePrivate(PrivateFood food){
        try (FoodDao dao = daoFactory.createFoodDao()){
            dao.savePrivate(food);
        }
    }
    public void countAllCalories(FoodCountMapDTO dto){
        dto.setCalories(dto.getMap().keySet().stream()
                .mapToInt(a->(int)((a.getCarbohydrate()*4
                        + a.getFats()*9
                        + a.getProtein()*4)
                        * dto.getMap().get(a)))
                .sum());
    }
}
