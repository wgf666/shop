package com.xsc.register.feign;

import dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REGISTER-SERVICE")
public interface registerService {
    @RequestMapping("/register/sms")
    ResultBean sendSms(@RequestParam(value = "phoneNumber") String phoneNumber);
    @RequestMapping("/register/mail")
    ResultBean sendmail(@RequestParam(value = "mail")String mail );

}
