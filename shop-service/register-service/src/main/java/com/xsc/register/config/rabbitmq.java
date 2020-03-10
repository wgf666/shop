package com.xsc.register.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitmq {
    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange("sms-exchange",true,false,null);
    }

}
