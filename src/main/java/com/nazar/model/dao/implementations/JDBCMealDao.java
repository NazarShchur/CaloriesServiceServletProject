package com.nazar.model.dao.implementations;

import com.nazar.model.dao.implementations.queries.MealSQL;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.FoodCountMapper;
import com.nazar.model.dao.mapper.implementations.MealMapper;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.Meal;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCMealDao implements MealDao {
    private Connection connection;

    private final static Logger logger = Logger.getLogger(JDBCMealDao.class);

    public JDBCMealDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Meal meal) {
        try (PreparedStatement createMeal = connection.prepareStatement(MealSQL.CREATE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addFood = connection.prepareStatement(MealSQL.ADD_FOOD_TO_MEAL)) {
            connection.setAutoCommit(false);
            createMeal.setString(1, meal.getDescription());
            createMeal.setInt(2, meal.getUserId());
            logger.debug("Executing SQL query " + MealSQL.CREATE + " for " + meal);
            createMeal.executeUpdate();
            ResultSet rs = createMeal.getGeneratedKeys();
            int currentMealID = 0;

            if (rs.next()) {
                currentMealID = rs.getInt(1);
            }

            for (Map.Entry<Food, Integer> entry : meal.getFoodMap().entrySet()) {
                addFood.setInt(1, entry.getKey().getId());
                addFood.setInt(2, currentMealID);
                addFood.setInt(3, entry.getValue());
                logger.debug("Executing SQL query " + MealSQL.ADD_FOOD_TO_MEAL + " for " + entry + "Meal id = {" + currentMealID + "}");
                addFood.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("SQLException occurred", e);
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Meal findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Meal> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Meal meal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        logger.debug("Closing connection");
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public List<Meal> findAllByUserId(int id) {
        Mapper<Meal> mealMapper = new MealMapper();
        List<Meal> meals = new ArrayList<>();
        try (PreparedStatement findMeals = connection.prepareStatement(MealSQL.FIND_BY_USER_ID)) {
            findMeals.setInt(1, id);
            logger.debug("Executing SQL query: " + MealSQL.FIND_BY_USER_ID + " {" + id + "}");
            ResultSet mealSet = findMeals.executeQuery();
            while (mealSet.next()) {
                meals.add(mealMapper.getEntity(mealSet));
            }
            for (Meal m : meals) {
                m.setFoodMap(findFoodByMealId(m.getId()));
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            throw new RuntimeException(e);
        }
        return meals;
    }

    @Override
    public Map<Food, Integer> findFoodByMealId(int id) {
        Map<Food, Integer> map = new HashMap<>();
        Mapper<Map<Food, Integer>> mapper = new FoodCountMapper();
        try (PreparedStatement ps = connection.prepareStatement(MealSQL.GET_FOOD_FROM_MEAL)) {
            ps.setInt(1, id);
            logger.debug("Executing SQL query: " + MealSQL.GET_FOOD_FROM_MEAL + " {" + id + "}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                map.putAll(mapper.getEntity(rs));
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            throw new RuntimeException(e);
        }
        return map;
    }
}
