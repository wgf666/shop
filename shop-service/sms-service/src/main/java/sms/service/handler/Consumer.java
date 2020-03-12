package sms.service.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sms.service.Util.SmsUtil;
import util.StringUtil;

import java.util.concurrent.TimeUnit;

@Component
public class Consumer {
    @Value("${sms.account}")
    private String account;

    @Value("${sms.password}")
    private String password;

    @Value("${sms.veryCode}")
    private String veryCode;

    @Value("${sms.http_url}")
    private String http_url;

    @Value("${sms.tplId}")
    private String tplId;
    @Autowired
    private RedisTemplate redisTemplate;
   @RabbitListener(queues = "sms-queue")
    public void process(String phoneNumber){
     //发送短信
    String code = String.valueOf((int) (((Math.random() * 9 + 1) * 1000)));
    String content="@1@="+code;
    SmsUtil.account = account;
    SmsUtil.password = password;
    SmsUtil.veryCode = veryCode;
    SmsUtil.http_url  =http_url;
    SmsUtil.sendTplSms(phoneNumber,tplId,content);

    //键值对，存入redis中
       String redisKey = StringUtil.getRedisKey("register:phone:", phoneNumber);
       //设置键值对和有效期
    redisTemplate.opsForValue().set(redisKey,code,5, TimeUnit.MINUTES);

   }

}
