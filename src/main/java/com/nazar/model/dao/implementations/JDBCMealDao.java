package com.nazar.model.dao.implementations;

import com.nazar.model.dao.implementations.queries.MealSQL;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.entity.Meal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCMealDao implements MealDao {
    private Connection connection;

    public JDBCMealDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(Meal meal){
        try(PreparedStatement ps = connection.prepareStatement(MealSQL.CREATE)) {
            ps.setString(1, meal.getDescription());
            ps.setDate(2, Date.valueOf(meal.getAddTime()));
            ps.setInt(3, meal.getUser().getId());
            ps.executeUpdate();
        }  catch (SQLException e){
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
}
