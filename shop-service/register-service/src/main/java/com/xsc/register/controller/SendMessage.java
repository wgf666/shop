package com.xsc.register.controller;

import dto.ResultBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/register")
public class SendMessage {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ResponseBody
    @RequestMapping("/sms")
    public ResultBean sms(String phoneNumber){
        rabbitTemplate.convertAndSend("qf.shop.sms",phoneNumber);
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage("success");
        resultBean.setErrno(0);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping("/mail")
    public ResultBean mail(String mail){
        //1.生成uuid
        String uuid = UUID.randomUUID().toString();



        rabbitTemplate.convertAndSend("qf.shop.mail",mail);
        ResultBean resultBean = new ResultBean();
        resultBean.setMessage("success");
        resultBean.setErrno(0);
        return resultBean;
    }

}
