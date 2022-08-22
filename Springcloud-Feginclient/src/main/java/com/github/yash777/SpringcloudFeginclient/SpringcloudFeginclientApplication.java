package com.github.yash777.SpringcloudFeginclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@org.springframework.cloud.openfeign.EnableFeignClients // @EnableFeignClients to enable component scanning for any interfaces that use @FeignClient.
public class SpringcloudFeginclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudFeginclientApplication.class, args);
	}

}
