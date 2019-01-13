package com.bin.service;

import com.bin.dao.registerDao;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: Bhy
 * @Date: 2019/1/12
 */
public class registerService {
    registerDao mRegisterDao = new registerDao();
    public String checkNameUseable(String name){
        boolean isHas = mRegisterDao.checkNameUseable(name);
        if (isHas){
            return "1";
        }
        else{
            return "0";
        }

    }

    public boolean registerSuccess(String userId,String pwd){
        if (checkNameUseable(userId).equals("1"))return false;
        pwd = DigestUtils.md5Hex(pwd);
        mRegisterDao.registSuccess(userId,pwd);
        return true;
    }
}
