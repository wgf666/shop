package sso.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan(value = "mapper")
public class SsoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoWebApplication.class, args);
    }

}
