package com.nazar.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    public T getEntity(ResultSet resultSet) throws SQLException;
}
