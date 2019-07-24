package com.nazar.model.dao.mapper.implementations;
import com.nazar.model.dao.implementations.queries.fieldsdb.FoodFields;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.entity.Food;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodMapper implements Mapper<Food> {
    @Override
    public Food getEntity(ResultSet resultSet) throws SQLException {
        return Food.builder()
                .id(resultSet.getInt(FoodFields.ID))
                .name(resultSet.getString(FoodFields.NAME))
                .carbohydrate(resultSet.getDouble(FoodFields.CARBOHYDRATE))
                .fats(resultSet.getDouble(FoodFields.FATS))
                .protein(resultSet.getDouble(FoodFields.PROTEIN))
                .publicFood(resultSet.getBoolean(FoodFields.ISPUBLIC))
                .build();
    }
}
