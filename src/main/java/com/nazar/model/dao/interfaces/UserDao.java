package com.nazar.model.dao.interfaces;

import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;

import java.util.Set;

public interface UserDao extends CRUDDao<User> {
    @Override
    void create(User user) throws NotUniqueLoginException;
    User findUserByLoginAndPassword(LoginUserDTO user);
    Set<Role> findUserRolesByID(int id);
    void addUserRole(int userID, Role role);
    int findIdByLogin(String login);
}
