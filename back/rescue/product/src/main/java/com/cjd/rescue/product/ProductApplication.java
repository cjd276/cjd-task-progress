package com.cjd.rescue.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cjd.rescue")
@MapperScan("com.cjd.rescue.dao")
@EnableScheduling
@EnableAsync
public class ProductApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
