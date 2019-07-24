package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.entity.Food;

import java.util.List;

public class FoodService{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Food> getAllFoodList(){
        try(FoodDao dao = daoFactory.createFoodDao()) {
           return dao.findAll();
        }
    }
    public List<Food> getFoodListByIsPublic(boolean isPublic){
        try(FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findByIsPublic(isPublic);
        }
    }
    public List<Food> getFoodListByUserID(int id){
        try(FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findByUserID(id);
        }
    }

}
