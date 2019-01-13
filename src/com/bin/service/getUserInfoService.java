package com.bin.service;

import com.bin.dao.basicDao;

import com.bin.bean.userInfo;
import com.google.gson.Gson;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
public class getUserInfoService {
    public String getInfo(String userId){
        userInfo info = new basicDao().getInfo(userId);

        String json = new Gson().toJson(info);

        return json;
    }

}
