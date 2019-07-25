package com.nazar.model.dao.implementations;

import com.nazar.model.dao.implementations.queries.MealSQL;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.FoodCountMapper;
import com.nazar.model.dao.mapper.implementations.MealMapper;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCMealDao implements MealDao {
    private Connection connection;

    public JDBCMealDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Meal meal){
        try(PreparedStatement createMeal = connection.prepareStatement(MealSQL.CREATE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement addFood = connection.prepareStatement(MealSQL.ADDFOODTOMEAL)){
            connection.setAutoCommit(false);
            createMeal.setString(1, meal.getDescription());
            createMeal.setDate(2, Date.valueOf(meal.getAddTime()));
            createMeal.setInt(3, meal.getUserId());
            createMeal.executeUpdate();
            ResultSet rs = createMeal.getGeneratedKeys();
            int currentMealID = 0;
            if(rs.next()){
                currentMealID = rs.getInt(1);
            }
            for(Map.Entry<Food, Integer> entry : meal.getFoodMap().entrySet()){
                addFood.setInt(1, entry.getKey().getId());
                addFood.setInt(2, currentMealID);
                addFood.setInt(3, entry.getValue());
                addFood.executeUpdate();
            }
            connection.commit();
        }  catch (SQLException e){
            try {
                connection.rollback();
            }catch (SQLException ex){
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Meal findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Meal meal) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }


    @Override
    public List<Meal> findAllByUserId(int id) { //todo ???
        Mapper<Meal> mealMapper = new MealMapper();
        List<Meal> meals = new ArrayList<>();
        try(PreparedStatement findMeals = connection.prepareStatement(MealSQL.FINDBYUSERID)){
            findMeals.setInt(1, id);
            ResultSet mealSet = findMeals.executeQuery();
            while (mealSet.next()){
                meals.add(mealMapper.getEntity(mealSet));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return meals;
    }

    @Override
    public Map<Food, Integer> findFoodByMealId(int id) {
        Map<Food, Integer> map = new HashMap<>();
        Mapper<Map<Food, Integer>> mapper = new FoodCountMapper();
        try (PreparedStatement ps = connection.prepareStatement(MealSQL.GETFOODFROMMEAL)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println("EXECUTED ASDSA" + MealSQL.GETFOODFROMMEAL);
            while (rs.next()){
                System.out.println((mapper.getEntity(rs) + "FOUND FOOD LIST"));
               map.putAll(mapper.getEntity(rs));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return map;
    }
}
