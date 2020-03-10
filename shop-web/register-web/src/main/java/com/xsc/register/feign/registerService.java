package com.xsc.register.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "register-service")
public interface registerService {
    @RequestMapping("/register/sms")
    public String  sendSms(@RequestParam(value = "phoneNumber") String phoneNumber);
}
