package com.nazar.model.dao.implementations;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dao.interfaces.UserDao;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
    @Override
    public FoodDao createFoodDao() {
        return new JDBCFoodDao(getConnection());
    }
    @Override
    public MealDao createMealDao() {
        return new JDBCMealDao(getConnection());
    }

    private Connection getConnection() {
        try {
            logger.debug("Getting connection to DB");
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Cannot get connection to db");
            throw new RuntimeException(e);
        }
    }
}
