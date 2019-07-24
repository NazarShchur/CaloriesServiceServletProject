package com.nazar.model.dao.implementations;

import com.mysql.cj.xdevapi.SqlDataResult;
import com.nazar.model.dao.implementations.queries.UserSQL;
import com.nazar.model.dao.implementations.queries.fieldsdb.UserFields;
import com.nazar.model.dao.implementations.queries.fieldsdb.UserRolesFields;
import com.nazar.model.dao.interfaces.UserDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.UserMapper;
import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUserByLoginAndPassword(LoginUserDTO user) {
        Mapper<User> userMapper = new UserMapper();
        User found = new User();
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.FINDBYLOGINANDPASSWORD)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            System.out.println("Executed " + UserSQL.FINDBYLOGINANDPASSWORD);
            if (rs.next()) {
                found = userMapper.getEntity(rs);
                found.setRoles(findUserRolesByID(found.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return found;
    }

    @Override
    public Set<Role> findUserRolesByID(int id) {
        Set<Role> roleSet = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.FINDROLESBYID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println("Executed " + UserSQL.FINDROLESBYID);
            while (rs.next()) {
                roleSet.add(Role.valueOf(rs.getString(UserRolesFields.ROLE)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roleSet;
    }

    @Override
    public void addUserRole(int userID, Role role) {
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.ADDUSERROLE)) {
            ps.setString(1, role.toString());
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findIdByLogin(String login) {
        int id = 0;
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.FINDIDBYLOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            System.out.println("Executed " + UserSQL.FINDIDBYLOGIN);
            if (rs.next()) {
                id = rs.getInt(UserFields.ID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    @Override
    public void create(User entity){
        try (PreparedStatement createUser = connection.prepareStatement(UserSQL.SAVE);
             PreparedStatement addRole = connection.prepareStatement(UserSQL.ADDUSERROLE)) {
            connection.setAutoCommit(false);
            createUser.setString(1, entity.getLogin());
            createUser.setString(2, entity.getPassword());
            createUser.setInt(3, entity.getAge());
            createUser.setString(4, entity.getGender().toString());
            createUser.setInt(5, entity.getHeight());
            createUser.setInt(6, entity.getWeight());
            createUser.setString(7, entity.getLifeStyle().toString());
            createUser.executeUpdate();
            addRole.setString(1, Role.USER.toString());
            addRole.setInt(2, findIdByLogin(entity.getLogin()));
            addRole.executeUpdate();
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new NotUniqueLoginException(e);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
