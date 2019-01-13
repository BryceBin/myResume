package com.bin.service;

import java.util.Random;

/**
 * @Author: Bhy
 * @Date: 2019/1/8
 */
public class captchaService {
    public static String captcha = "1234";
    public String getNewCaptcha(){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<4;i++){
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                case 0:
                    result=Math.round(Math.random()*25+65);
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        captcha = sb.toString();
        return captcha;
    }
}
