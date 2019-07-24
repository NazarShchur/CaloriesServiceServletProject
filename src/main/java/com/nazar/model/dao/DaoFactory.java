package com.nazar.model.dao;

import com.nazar.model.dao.implementations.JDBCDaoFactory;
import com.nazar.model.dao.interfaces.FoodDao;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dao.interfaces.UserDao;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract FoodDao createFoodDao();
    public abstract MealDao createMealDao();
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

