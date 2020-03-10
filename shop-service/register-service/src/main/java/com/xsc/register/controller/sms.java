package com.xsc.register.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
public class sms {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ResponseBody
    @RequestMapping("/sms")
    public void sms(String phoneNumber){
        rabbitTemplate.convertAndSend("topic-exchange",phoneNumber);
    }

}
