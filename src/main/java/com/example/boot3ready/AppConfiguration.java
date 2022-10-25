package com.example.boot3ready;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AppConfiguration {

	@Bean
	HelloService helloService () {
		return new HelloService();
	}

}
