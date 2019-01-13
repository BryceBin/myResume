package com.bin.dao;

import java.sql.*;

import com.bin.config.personalMessage;

/**
 * @Author: Bhy
 * @Date: 2019/1/8
 */
public class loginDao {

    public boolean checkLogin(String id,String pwd){
        Connection connection = null;
        Statement statement = null;
        try {
            //注册JDBC驱动
            Class.forName(personalMessage.jdbc_driver).newInstance();

            //打开链接
            connection = DriverManager.getConnection(personalMessage.db_url,personalMessage.user,personalMessage.password);

            //执行查询
            statement = connection.createStatement();
            String sql = String.format("select pwd from users where userId = '%s' ",id);
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                String password = resultSet.getString("pwd");
                if (password.equals(pwd)){
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return false;
    }
}
