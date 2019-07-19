package com.nazar.model.dao.interfaces;

import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;

public interface UserDao extends CRUDDao<User> {
    @Override
    void create(User user) throws NotUniqueLoginException;
    User findUserByLoginAndPassword(LoginUserDTO user);
}
