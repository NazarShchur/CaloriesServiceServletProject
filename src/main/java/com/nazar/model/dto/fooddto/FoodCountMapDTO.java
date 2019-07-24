package com.nazar.model.dto.fooddto;

import com.nazar.model.entity.Food;

import java.util.HashMap;
import java.util.Map;

public class FoodCountMapDTO {
    private Map<Food, Integer> map;
    public FoodCountMapDTO(){
        map = new HashMap<>();
    }
    public Map<Food, Integer> getMap(){
        return map;
    }
}
