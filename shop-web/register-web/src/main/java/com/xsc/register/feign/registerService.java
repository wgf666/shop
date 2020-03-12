package com.xsc.register.feign;

import dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.Result;

@FeignClient(name = "REGISTER-SERVICE" )
public interface registerService {
    @RequestMapping("/register/sms")
    ResultBean sms(@RequestParam(value = "phoneNumber") String phoneNumber);
    @RequestMapping("/register/mail")
    ResultBean sendmail(@RequestParam(value = "mail")String mail,@RequestParam(value = "password")String password );
    @RequestMapping("/register/phone")
    ResultBean phone(@RequestParam(value="phoneNumber") String phoneNumber,@RequestParam(value="code") String code,@RequestParam(value="password") String password);
}
