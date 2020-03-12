package com.xsc.register.controller;

import com.xsc.register.feign.registerService;
import dto.ResultBean;
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
        registerService.sms(phoneNumber);

    }
    @RequestMapping("phone")
    public String phone(@RequestParam(value="phoneNumber") String phoneNumber,@RequestParam(value="code") String code,@RequestParam(value="password") String password){
        ResultBean phone = registerService.phone(phoneNumber, code, password);
        if(phone.getErrno()==0){
            return "login";
        }
        return "register";
    }

    @RequestMapping("/mail")
    public String mail(@RequestParam(value = "mail")String mail,@RequestParam(value = "password")String password){
        ResultBean sendmail = registerService.sendmail(mail, password);
        if(sendmail.getErrno()==0){
            return "login";
        }
        return "register";
    }

}
