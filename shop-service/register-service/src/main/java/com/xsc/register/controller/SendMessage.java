package com.xsc.register.controller;

import dto.EmailMessageDTO;
import dto.ResultBean;
import entity.TUser;
import mapper.TUserMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.StringUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/register")
public class SendMessage {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private  RedisTemplate redisTemplate;
    @ResponseBody
    @RequestMapping("/sms")
    public ResultBean sms(String phoneNumber){
        rabbitTemplate.convertAndSend("sms-exchange","qf.shop.sms",phoneNumber);

        return ResultBean.success("发送短信成功");
    }
    @ResponseBody
    @RequestMapping("/mail")
    public ResultBean mail(@RequestParam(value = "mail")String mail, @RequestParam(value = "password")String password ){
        //1.生成uuid,生成激活网站，
        String uuid = UUID.randomUUID().toString();
        String url="http://localhost:8000/active/account/"+uuid;
        EmailMessageDTO message= new EmailMessageDTO();
        message.setUrl(url);
        message.setEmail(mail);
        //发送给mq，发送邮件

        rabbitTemplate.convertAndSend("sms-exchange","qf.shop.mail",message);

        //向redis中添加uuid， 向数据库中创建用户信息
        String redisKey = StringUtil.getRedisKey("register:email:", uuid);
        redisTemplate.opsForValue().set(redisKey,mail,5, TimeUnit.MINUTES);

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
