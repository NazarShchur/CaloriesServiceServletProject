package com.nazar.service;

import com.nazar.model.dao.DaoFactory;
import com.nazar.model.dao.interfaces.UserDao;
import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;


public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveNewUser(User user){
        try (UserDao dao = daoFactory.createUserDao()) {//todo transaction
            dao.create(user);
        }
        System.out.println("saved " + user);
    }

    public User auth(LoginUserDTO user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findUserByLoginAndPassword(user);
        }
    }

}
