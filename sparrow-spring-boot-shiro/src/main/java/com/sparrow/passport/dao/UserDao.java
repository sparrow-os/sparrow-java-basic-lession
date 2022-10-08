package com.sparrow.passport.dao;

import com.sparrow.passport.entity.User;
import com.sparrow.passport.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User getUserByUserName(String userName,String password) throws SQLException {
        //ORM (java Oop Relation database Mapping)
        //MVC 解耦 复用
        //IOC 控制反转
        //Sparrow
        Connection connection = JDBCUtils.getConnection();
        ResultSet rs = JDBCUtils.query(connection, "SELECT user_name,`password` FROM `user` WHERE user_name='" + userName + "' and password='"+password+"' limit 1");
        try {
            if (rs != null) {
                User user = null;
                if (rs.next()) {
                    user = new User();
                    password = rs.getString("password");
                    user.setUserId(rs.getLong("user_id"));
                    user.setUserName(userName);
                    user.setPassword(password);
                }
                return user;
            }
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
