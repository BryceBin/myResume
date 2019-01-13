package com.bin.service;

import java.util.Map;
import com.bin.service.captchaService;
import com.bin.dao.*;
import org.apache.commons.codec.digest.DigestUtils;
import com.bin.service.captchaService;

/**
 * @Author: Bhy
 * @Date: 2019/1/8
 */
public class loginService {
    //检查用户输入是否正确， 正确则可以跳转到登陆成功界面
    public boolean checkLogin(Map<String,String> map){
        String flag = map.get("flag");
        if (flag.equals("account")) {

            String captcha = map.get("captcha");
            if (!captcha.equals(captchaService.captcha)){
                System.out.println("captcha("+captcha+") !="+captchaService.captcha+"\n");
                return false;
            }
            loginDao loginDao = new loginDao();
            return loginDao.checkLogin(map.get("username"),DigestUtils.md5Hex(map.get("password")));
        }
        else{
            return map.get("captcha").equals(sendSMSService.phoneCaptcha);
        }
    }
}
