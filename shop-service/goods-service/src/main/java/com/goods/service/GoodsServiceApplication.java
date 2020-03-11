package com.goods.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("mapper")
public class GoodsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsServiceApplication.class, args);
	}

}
