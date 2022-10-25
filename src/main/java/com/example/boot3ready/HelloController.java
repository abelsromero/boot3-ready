package com.example.boot3ready;


import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

	private final HelloService helloService;
	private final ApplicationContext applicationContext;

	HelloController(HelloService helloService, ApplicationContext applicationContext) {
		this.helloService = helloService;
		this.applicationContext = applicationContext;
	}

	@GetMapping(value = {"/hello/{username}"})
	public Message hello(@PathVariable(required = false) String username, @RequestParam String source) throws NoSuchMethodException, ClassNotFoundException {

		String responseText = switch (source) {
			case "autowired" -> helloService.hello(username);
			case "context" -> ((HelloService) applicationContext.getBean("helloService")).hello(username);
			case "reflection" -> reflectionInvocation(username);
			default -> "who?";
		};

		return new Message(responseText);
	}

	private String reflectionInvocation(String username) throws NoSuchMethodException, ClassNotFoundException {
		Class<?> aClass = ClassUtils.forName(HelloService.class.getName(), getClass().getClassLoader());
		Method helloMethod = aClass.getMethod("hello", String.class);
		Object helloService = BeanUtils.instantiateClass(aClass);
		return (String) ReflectionUtils.invokeMethod(helloMethod, helloService, username);
	}

	record Message(String text) { }
}
