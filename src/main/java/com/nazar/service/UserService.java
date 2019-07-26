package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.MealDao;
import com.nazar.model.dao.interfaces.UserDao;
import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.Meal;
import com.nazar.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class UserService {
    private final String USER = "user";
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveNewUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
        }
        System.out.println("saved " + user);
    }

    public User auth(LoginUserDTO user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findUserByLoginAndPassword(user);
        }
    }

    public void countDailyCalories(User user) {
        user.setDailyCalories((int)
                ((user.getGender().getBaseCalories()
                        + user.getGender().getWeightC() * user.getWeight()
                        + user.getGender().getHeightC() * user.getHeight()
                        + user.getGender().getAgeC() * user.getAge())
                        * user.getLifeStyle().getAmr())
        );
    }
    public User getCurrentUser(HttpServletRequest request){
        return (User)request.getSession().getAttribute(USER);
    }
}
