package com.xsc.register.controller;

import dto.EmailMessageDTO;
import dto.ResultBean;
import entity.TUser;
import mapper.TUserMapper;
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
    @Autowired
    private TUserMapper tUserMapper;
    @ResponseBody
    @RequestMapping("/sms")
    public ResultBean sms(String phoneNumber){
        rabbitTemplate.convertAndSend("qf.shop.sms",phoneNumber);

        return ResultBean.success("发送短信成功");
    }
    @ResponseBody
    @RequestMapping("/mail")
    public ResultBean mail(String mail,String password){
        //1.生成uuid,生成激活网站，
        String uuid = UUID.randomUUID().toString();
        String url="http://localhost:8000/active/account/"+uuid;
        EmailMessageDTO message= new EmailMessageDTO();
        message.setUrl(url);
        message.setEmail(mail);
        //发送给mq，发送邮件
        rabbitTemplate.convertAndSend("qf.shop.mail",message);
        TUser user = new TUser();
        user.setName(mail);
        user.setStatus("0");
        user.setEmail(mail);
        user.setPassword(password);
        //在数据库中创建账号,状态为未激活。
        int i = tUserMapper.insertSelective(user);
        return ResultBean.success("发送成功");
    }

}
