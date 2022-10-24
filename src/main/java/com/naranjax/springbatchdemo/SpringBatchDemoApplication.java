package com.naranjax.springbatchdemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableBatchProcessing
//@ComponentScan({"com.naranjax.springbatchdemo.repository", "com.naranjax.springbatchdemo.controller", "com.naranjax.springbatchdemo.model", "com.naranjax.springbatchdemo.paymentStep", "com.naranjax.springbatchdemo.config"})
public class SpringBatchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}

}
