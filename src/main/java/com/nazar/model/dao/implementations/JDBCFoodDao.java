package com.nazar.model.dao.implementations;
import com.nazar.model.dao.implementations.queries.FoodSQL;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.FoodMapper;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCFoodDao implements FoodDao {
    private Connection connection;

    public JDBCFoodDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Food entity) throws Exception {

    }

    @Override
    public Food findById(int id) {
        Mapper<Food> mapper = new FoodMapper();
        Food found = new Food();
        try(PreparedStatement ps = connection.prepareStatement(FoodSQL.FINDBYID)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                found = mapper.getEntity(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return found;
    }

    @Override
    public List<Food> findAll() {
        Mapper<Food> mapper = new FoodMapper();
        List<Food> foodList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FoodSQL.FINDALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                foodList.add(mapper.getEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodList;
    }

    @Override
    public void update(Food entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<Food> findByIsPublic(boolean isPublic){
        Mapper<Food> mapper = new FoodMapper();
        List<Food> foodList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FoodSQL.FINDBYISPUBLIC)) {
            ps.setBoolean(1, isPublic);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                foodList.add(mapper.getEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodList;
    }

    @Override
    public List<Food> findByUserID(int id) {
        Mapper<Food> mapper = new FoodMapper();
        List<Food> foodList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FoodSQL.FINDBYUSERID)) {
            ps.setInt(1, id);
            System.out.println("Executing query " + FoodSQL.FINDBYUSERID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                foodList.add(mapper.getEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodList;
    }
}
