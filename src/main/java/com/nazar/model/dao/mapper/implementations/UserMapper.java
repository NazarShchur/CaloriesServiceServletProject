package com.nazar.model.dao.mapper.implementations;

import com.nazar.model.dao.implementations.queries.fieldsdb.UserFields;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.entity.Gender;
import com.nazar.model.entity.LifeStyle;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

public class UserMapper implements Mapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt(UserFields.ID))
                .login(resultSet.getString(UserFields.LOGIN))
                .password(resultSet.getString(UserFields.PASSWORD))
                .age(resultSet.getInt(UserFields.AGE))
                .gender(Gender.valueOf(resultSet.getString(UserFields.GENDER)))
                .height(resultSet.getInt(UserFields.HEIGHT))
                .weight(resultSet.getInt(UserFields.WEIGHT))
                .lifeStyle(LifeStyle.valueOf(resultSet.getString(UserFields.LIFE_STYLE)))
                .build();
    }
}
