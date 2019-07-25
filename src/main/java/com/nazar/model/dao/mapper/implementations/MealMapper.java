package com.nazar.model.dao.mapper.implementations;

import com.nazar.model.dao.implementations.queries.fieldsdb.MealFields;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.entity.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class MealMapper implements Mapper<Meal> {
    @Override
    public Meal getEntity(ResultSet resultSet) throws SQLException {
        return Meal.builder()
                .id(resultSet.getInt(MealFields.ID))
                .addTime(resultSet.getDate(MealFields.ADD_TIME).toLocalDate())
                .description(resultSet.getString(MealFields.DESCRIPTION))
                .userId(resultSet.getInt(MealFields.USER_ID))
                .build();
    }
}
