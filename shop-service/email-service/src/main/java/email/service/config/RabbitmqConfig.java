package email.service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue getQueue(){
        return new Queue("mail-queue");
    }
    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange("sms-exchange",true,false,null);
    }
    @Bean
    public Binding getBinding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("qf.shop.mail");
    }

}



