package com.nazar.model.dao.mapper.implementations;

import com.nazar.model.dao.implementations.queries.fieldsdb.MealFoodFields;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FoodCountMapper implements Mapper<Map<Food, Integer>> {
    @Override
    public Map<Food, Integer> getEntity(ResultSet resultSet) throws SQLException {
        Map<Food, Integer> map = new HashMap<>();
        Mapper<Food> foodMapper = new FoodMapper();
        map.put(foodMapper.getEntity(resultSet), resultSet.getInt(MealFoodFields.FOOD_COUNT));
        return map;
    }
}
