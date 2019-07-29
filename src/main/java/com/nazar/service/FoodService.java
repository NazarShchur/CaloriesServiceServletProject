package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dto.fooddto.CheckFoodDTO;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;
import com.nazar.model.myexceptions.UnacceptableDataInputException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public class FoodService {
    private final static Logger logger = Logger.getLogger(FoodService.class);
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

    public List<Food> getAvailableFoodList(Map<Food, Integer> map, int id) {
        logger.debug("Forming food list for user " + id);
        List<Food> foodList = new ArrayList<>();
        foodList.addAll(getPrivateFoodListByUserID(id));
        foodList.addAll(getPublicFoodList());
        return foodList.stream()
                .distinct()
                .filter(f -> !map.keySet().contains(f))
                .sorted(Comparator.comparing(Food::getName))
                .collect(Collectors.toList());
    }
    public Food findByID(int id) {
        try (FoodDao dao = daoFactory.createFoodDao()) {
            return dao.findById(id);
        }
    }
    public void savePrivate(PrivateFood food){
        try (FoodDao dao = daoFactory.createFoodDao()){
            dao.savePrivate(food);
        }
        logger.debug("Saved food " + food);
    }
    public void countAllCalories(FoodCountMapDTO dto) {
        dto.setCalories(dto.getMap().keySet().stream()
                .mapToInt(a -> (int) ((a.getCarbohydrate() * 4
                        + a.getFats() * 9
                        + a.getProtein() * 4)
                        * dto.getMap().get(a)))
                .sum());
    }
    public PrivateFood checkIsValidDataAndReturnFood(CheckFoodDTO dto) throws UnacceptableDataInputException{
        logger.debug("Checking is food data valid " + dto);
        PrivateFood food = new PrivateFood();
        try {
            food.setCarbohydrate(Double.parseDouble(dto.getCarbohydrate()));
            food.setFats(Double.parseDouble(dto.getFats()));
            food.setProtein(Double.parseDouble(dto.getProtein()));
        } catch (Exception e){
            logger.error("Food data is invalid", e);
            throw new UnacceptableDataInputException(e);
        }
        food.setName(dto.getName());
        return food;
    }
}
