package com.example.boot3ready;

import org.springframework.stereotype.Component;

public class HelloService {

	public String hello (String username) {
		return String.format("Hello %s", username);
	}

}
