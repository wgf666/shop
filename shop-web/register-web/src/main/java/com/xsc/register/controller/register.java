package com.xsc.register.controller;

import com.xsc.register.feign.registerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class register {
    @Autowired
    private registerService registerService;

    @RequestMapping("")
    public String register(){
        return "register";
    }

    //feign调用
    @RequestMapping("/sms")
    public void sms(@RequestParam(value="phoneNumber") String phoneNumber){
          registerService.sendSms(phoneNumber);
    }
    @RequestMapping("/mail")
    public void mail(@RequestParam(value="mail") String mail,@RequestParam(value = "password")String password){
        registerService.sendmail(mail,password);
    }

}
