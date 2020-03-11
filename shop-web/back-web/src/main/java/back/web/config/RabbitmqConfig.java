package back.web.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange("indetermination");
    }
}
