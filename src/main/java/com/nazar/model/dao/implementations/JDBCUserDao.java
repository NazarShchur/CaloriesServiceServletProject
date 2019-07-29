package com.nazar.model.dao.implementations;

import com.nazar.model.dao.implementations.queries.UserSQL;
import com.nazar.model.dao.implementations.queries.fieldsdb.UserRolesFields;
import com.nazar.model.dao.interfaces.UserDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.UserMapper;
import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.Role;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    private final static Logger logger = Logger.getLogger(JDBCUserDao.class);

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUserByLoginAndPassword(LoginUserDTO user) {
        Mapper<User> userMapper = new UserMapper();
        User found = new User();
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.FIND_BY_LOGIN_AND_PASSWORD)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            logger.debug("Executing SQL query: " + UserSQL.FIND_BY_LOGIN_AND_PASSWORD + " for" + user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                found = userMapper.getEntity(rs);
                found.setRoles(findUserRolesByID(found.getId()));
            }
        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            throw new RuntimeException(e);
        }
        return found;
    }
    @Override
    public Set<Role> findUserRolesByID(int id) {
        Set<Role> roleSet = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.FIND_ROLES_BY_ID)) {
            ps.setInt(1, id);
            logger.debug("Executing SQL query:" + UserSQL.FIND_ROLES_BY_ID + " {" + id + "}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                roleSet.add(Role.valueOf(rs.getString(UserRolesFields.ROLE)));
            }

        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            throw new RuntimeException(e);
        }
        return roleSet;
    }
    @Override
    public void create(User entity){
        try (PreparedStatement createUser = connection.prepareStatement(UserSQL.SAVE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addRole = connection.prepareStatement(UserSQL.ADD_USER_ROLE)) {
            connection.setAutoCommit(false);
            createUser.setString(1, entity.getLogin());
            createUser.setString(2, entity.getPassword());
            createUser.setInt(3, entity.getAge());
            createUser.setString(4, entity.getGender().toString());
            createUser.setInt(5, entity.getHeight());
            createUser.setInt(6, entity.getWeight());
            createUser.setString(7, entity.getLifeStyle().toString());
            logger.debug("Executing SQL query:" + UserSQL.SAVE + " for " + entity);
            createUser.executeUpdate();
            ResultSet rs = createUser.getGeneratedKeys();
            int userID = 0;
            if(rs.next()){
                userID = rs.getInt(1);
            }
            addRole.setString(1, Role.USER.toString());
            addRole.setInt(2, userID);
            logger.debug("Executing SQL query:" + UserSQL.ADD_USER_ROLE + " user_id={" + userID + "}");
            addRole.executeUpdate();
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("SQLException occurred", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new NotUniqueLoginException(entity.getLogin());
        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("SQLException occurred", e);
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        logger.debug("Closing connection");
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("SQLException occurred", e);
            throw new RuntimeException(e);
        }
    }
}
