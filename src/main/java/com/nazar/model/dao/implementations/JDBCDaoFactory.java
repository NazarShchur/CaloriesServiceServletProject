package com.nazar.model.dao.implementations;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dao.interfaces.UserDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private final String DB_URL = "jdbc:mysql://localhost:3306/servletdb?serverTimezone=GMT";
    private final String DB_PASSWORD = "root";
    private final String DB_USER = "root";
    private final static Logger logger = Logger.getLogger(JDBCDaoFactory.class);
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    public FoodDao createFoodDao() {
        return new JDBCFoodDao(getConnection());
    }

    public MealDao createMealDao() {
        return new JDBCMealDao(getConnection());
    }

    private Connection getConnection() {
        try {
            logger.debug("Getting connection to DB URL:{" + DB_URL + "} Password:{" + DB_PASSWORD + "} User:{" + DB_USER + "}");
            return DriverManager.getConnection(
                    DB_URL,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            logger.error("Cannot get connection to db");
            throw new RuntimeException(e);
        }
    }
}
