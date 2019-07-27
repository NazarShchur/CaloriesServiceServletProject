package com.nazar.model.dao.implementations;
import com.nazar.model.dao.implementations.queries.FoodSQL;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.FoodMapper;
import com.nazar.model.dao.mapper.implementations.PrivateFoodMapper;
import com.nazar.model.entity.Food;
import com.nazar.model.entity.PrivateFood;

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
    public void create(Food entity){
        try(PreparedStatement ps = connection.prepareStatement(FoodSQL.SAVE)){
            ps.setDouble(1, entity.getCarbohydrate());
            ps.setDouble(2, entity.getFats());
            ps.setDouble(3, entity.getProtein());
            ps.setString(4, entity.getName());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Food findById(int id) {
        Mapper<Food> mapper = new FoodMapper();
        Food found = new Food();
        try(PreparedStatement ps = connection.prepareStatement(FoodSQL.FIND_BY_ID)){
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
        try (PreparedStatement ps = connection.prepareStatement(FoodSQL.FIND_ALL)) {
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
    public List<Food> findPublicFood(){
        Mapper<Food> mapper = new FoodMapper();
        List<Food> foodList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FoodSQL.FIND_PUBLIC)) {
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
    public List<PrivateFood> findByUserID(int id) {
        Mapper<PrivateFood> mapper = new PrivateFoodMapper();
        List<PrivateFood> foodList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FoodSQL.FIND_BY_USER_ID)) {
            ps.setInt(1, id);
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
    public void savePrivate(PrivateFood food) {
        try(PreparedStatement ps = connection.prepareStatement(FoodSQL.SAVE_PRIVATE)){
            ps.setInt(1, food.getUserID());
            ps.setDouble(2, food.getCarbohydrate());
            ps.setDouble(3, food.getFats());
            ps.setDouble(4, food.getProtein());
            ps.setString(5, food.getName());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<PrivateFood> findPrivate(){
        List<PrivateFood> foodList = new ArrayList<>();
        Mapper<PrivateFood> foodMapper = new PrivateFoodMapper();
        try(PreparedStatement ps = connection.prepareStatement(FoodSQL.FIND_PRIVATE)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                foodList.add(foodMapper.getEntity(rs));
            }
            System.out.println("returned + " + foodList);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return foodList;
    }
    @Override
    public void updateToPublic(int id){
        try(PreparedStatement ps = connection.prepareStatement(FoodSQL.UPDATE_TO_PUBLIC)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
