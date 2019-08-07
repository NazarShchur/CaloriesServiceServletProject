package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dto.fooddto.CheckFoodDTO;
import com.nazar.model.dto.fooddto.FoodCountMapDTO;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;
import com.nazar.model.myexceptions.UnacceptableDataInputException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


public class FoodService {
    private final String FOOD_ID = "foodID";
    private final String CURRENT_MAP = "currentMap";
    private final String COUNT = "count";
    private final String IS_FOOD_IN_MAP = "isFoodInMap";
    private final String FOOD_ID_TO_DELETE = "foodIDToDelete";
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
            food.setName(dto.getName());
        } catch (Exception e){
            logger.error("Food data is invalid", e);
            throw new UnacceptableDataInputException(e);
        }
        return food;
    }
    public void updateFoodCart(HttpServletRequest request, FoodCountMapDTO map){
        countAllCalories(map);
        request.getSession().setAttribute(CURRENT_MAP, map);
        request.getSession().setAttribute(IS_FOOD_IN_MAP, map.getMap().size() > 0);
    }

    public void addFoodToCart(HttpServletRequest request){
        Food addedFood = findByID(Integer.parseInt(request.getParameter(FOOD_ID)));
        int countOfAddedFood = Integer.parseInt(request.getParameter(COUNT));
        FoodCountMapDTO currentMap = Optional.ofNullable((FoodCountMapDTO)request.getSession().getAttribute(CURRENT_MAP))
                .orElse(new FoodCountMapDTO());
        currentMap.getMap().put(addedFood, countOfAddedFood);
        updateFoodCart(request, currentMap);
    }

    public void deleteFoodFromCart(HttpServletRequest request){
        FoodCountMapDTO currentMap = (FoodCountMapDTO)request.getSession().getAttribute(CURRENT_MAP);
        Food foodToDeleteFromMeal = findByID(Integer.parseInt(request.getParameter(FOOD_ID_TO_DELETE)));
        currentMap.getMap().keySet().remove(foodToDeleteFromMeal);
        updateFoodCart(request, currentMap);
    }


}
