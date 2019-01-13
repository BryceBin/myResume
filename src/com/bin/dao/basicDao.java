package com.bin.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.bin.dao.registerDao;
import com.bin.bean.userInfo;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
public class basicDao {
    public userInfo getInfo(String userId){
        Connection connection = registerDao.openDB();
        PreparedStatement statement = null;
        userInfo info = new userInfo();
        try {
            String sql = "select * from userinfo where userid = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                info.setUserId(resultSet.getString("userId"));
                info.setUname(resultSet.getString("uname"));
                info.setJianjie(resultSet.getString("jianjie"));
                info.setEdujl(resultSet.getString("edujl"));
                info.setMajorbg(resultSet.getString("majorbg"));
                info.setHobby(resultSet.getString("hobby"));
                info.setHonor(resultSet.getString("honor"));
                info.setXmjl(resultSet.getString("xmjl"));
                info.setContact(resultSet.getString("contact"));
                info.setPhoto(resultSet.getString("photo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public boolean updateInfo(String userId,Map<String,String> info){
        Connection connection = registerDao.openDB();
        PreparedStatement statement = null;
        String sql = "update userInfo set uname=?,jianjie=?,edujl=?,majorbg=?,hobby=?,honor=?,xmjl=?,contact=?,photo=? where userId = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,info.get("uname"));
            statement.setString(2,info.get("jianjie"));
            statement.setString(3,info.get("edujl"));
            statement.setString(4,info.get("majorbg"));
            statement.setString(5,info.get("hobby"));
            statement.setString(6,info.get("honor"));
            statement.setString(7,info.get("xmjl"));
            statement.setString(8,info.get("contact"));
            statement.setString(9,info.get("photo"));
            statement.setString(10,userId);

            statement.executeUpdate();
            String phone = info.get("contact");
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            if (phone.length()==11&&pattern.matcher(phone).matches()){
                //手机号符合一定格式则更新账户表
                sql = "update users set phone = ? where userId = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1,phone);
                statement.setString(2,userId);
                statement.executeUpdate();
            }


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getName(String phone){
        Connection connection = registerDao.openDB();
        PreparedStatement statement = null;
        String sql = "select userId from users where phone = ?";
        String userId = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,phone);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                userId = resultSet.getString("userId");
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
