package com.bin.service;

import java.util.Map;
import com.bin.dao.basicDao;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
public class modifyInfoService {
    public void updateInfo(String userId, Map<String,String> info){
        if (new basicDao().updateInfo(userId,info)){
            System.out.println("update success!");
        }
    }
}
