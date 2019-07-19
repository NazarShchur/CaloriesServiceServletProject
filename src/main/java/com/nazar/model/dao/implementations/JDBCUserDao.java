package com.nazar.model.dao.implementations;

import com.nazar.model.dao.implementations.queries.UserSQL;
import com.nazar.model.dao.interfaces.UserDao;
import com.nazar.model.dao.mapper.Mapper;
import com.nazar.model.dao.mapper.implementations.UserMapper;
import com.nazar.model.dto.userdto.LoginUserDTO;
import com.nazar.model.entity.User;
import com.nazar.model.myexceptions.NotUniqueLoginException;

import java.sql.*;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findUserByLoginAndPassword(LoginUserDTO user){
        Mapper<User> userMapper = new UserMapper();
        User found = new User();
        try(PreparedStatement ps = connection.prepareStatement(UserSQL.FINDBYLOGINANDPASSWORD)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            System.out.println("Executed " + UserSQL.FINDBYLOGINANDPASSWORD);
            if(rs.next()){
                System.out.println("rs has next");
                found = userMapper.getEntity(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return found;
    }
    @Override
    public void create(User entity) throws NotUniqueLoginException{
        try (PreparedStatement ps = connection.prepareStatement(UserSQL.SAVE)) {
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NotUniqueLoginException(e);
        } catch (SQLException e){
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
