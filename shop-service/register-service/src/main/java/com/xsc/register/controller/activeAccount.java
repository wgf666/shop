package com.xsc.register.controller;

import dto.ResultBean;
import mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.StringUtil;

@RestController
public class activeAccount {
   @Autowired
   private RedisTemplate redisTemplate;
   @Autowired
   private TUserMapper userMapper;

   @RequestMapping("/active/account/{uuid}")
 private ResultBean active(@PathVariable String uuid){
     //1. 这是redis中的键。
        String redisKey = StringUtil.getRedisKey("register:email:", uuid);
     //从redis的键中获得邮箱账号
        String addr = (String) redisTemplate.opsForValue().get(redisKey);
        System.out.println(addr);
        if(addr==null){
         return  ResultBean.error("无法从redis中获取账户信息");
     }
        //将status从0改到1
     int active = userMapper.update(addr);
    if(active>0){
        return  ResultBean.success("从数据库中激活成功");
}
     return  ResultBean.error("激活失败");
    }



}
