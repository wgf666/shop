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

    //feign调用
    @RequestMapping("/sms")
    public String sms(@RequestParam(value="phoneNumber") String phoneNumber){

        return registerService.sendSms(phoneNumber);
    }

    @RequestMapping("")
    public String register(){
        return "register";
}

}
