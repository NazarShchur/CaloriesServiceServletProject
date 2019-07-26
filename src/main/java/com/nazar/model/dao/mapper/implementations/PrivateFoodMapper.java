package com.nazar.model.dao.mapper.implementations;

import com.nazar.model.dao.implementations.queries.fieldsdb.FoodFields;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.entity.PrivateFood;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrivateFoodMapper implements Mapper<PrivateFood> {
    @Override
    public PrivateFood getEntity(ResultSet resultSet) throws SQLException {
        return PrivateFood.builder()
                .id(resultSet.getInt(FoodFields.ID))
                .name(resultSet.getString(FoodFields.NAME))
                .carbohydrate(resultSet.getDouble(FoodFields.CARBOHYDRATE))
                .fats(resultSet.getDouble(FoodFields.FATS))
                .protein(resultSet.getDouble(FoodFields.PROTEIN))
                .userID(resultSet.getInt(FoodFields.USER_ID))
                .build();
    }
}
