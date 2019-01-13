package com.bin.dao;

import com.bin.config.personalMessage;

import java.sql.*;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
public class registerDao {
    //检查在数据库中是否存在值为name的userId
    public boolean checkNameUseable(String name){
        Connection connection = openDB();
        Statement statement = null;
        try {
            //执行查询
            statement = connection.createStatement();
            String sql = String.format("select count(*) cnt from users where userId = '%s' ",name);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()&&resultSet.getString("cnt").equals("1")){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //注册成功，插入注册的账号和密码
    public boolean registSuccess(String name,String pwd) {
        Connection connection = openDB();
        PreparedStatement statement = null;
        //执行插入
        try {
            String sql = "insert into users values(?,'',?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,pwd);
            statement.executeUpdate();

            sql = "insert into userInfo(userId) values(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //打开数据库
    public static Connection openDB(){
        Connection connection = null;
        Statement statement = null;
        try {
            //注册JDBC驱动
            Class.forName(personalMessage.jdbc_driver).newInstance();

            //打开链接
            connection = DriverManager.getConnection(personalMessage.db_url,personalMessage.user,personalMessage.password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
