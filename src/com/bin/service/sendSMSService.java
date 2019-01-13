package com.bin.service;

import javafx.util.converter.ShortStringConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;
import com.bin.config.personalMessage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @Author: Bhy
 * @Date: 2019/1/8
 */
public class sendSMSService {
    public static String phoneCaptcha;
    //生成随机验证码
    private String getCode(){
        phoneCaptcha = String.valueOf(new Random().nextInt(1000000-100000+1)+100000);
        return phoneCaptcha;
    }

    //发送验证码
    public void send(String phone){
        String code = getCode();
        String sid = personalMessage.sid;
        String token = personalMessage.token;
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String sig = DigestUtils.md5Hex(sid+token+timeStamp);
        String templateId = personalMessage.templateid;
        String param = code;
        String to = phone;
        String url = personalMessage.SMSUrl;

        System.out.println("time: "+timeStamp
        +"\nsig: "+sig+"\n"
        +"phone = "+phone+"\n"
        +"code = "+code+"\n");

        Connection connection = Jsoup.connect(url);
        connection.data("accountSid",sid);
        connection.data("templateid",templateId);
        connection.data("param",param);
        connection.data("to",to);
        connection.data("timestamp",timeStamp);
        connection.data("sig",sig);

        connection.header("Content-type","application/x-www-form-urlencoded");
        Document document = null;
        try{
            document = connection.post();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("\n\nresult = "+document.toString());
    }
}
