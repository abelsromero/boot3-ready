package com.example.boot3ready;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.util.ReflectionUtils;

public class HelloControllerRuntimeHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		Constructor<?> helloConstructor = HelloService.class.getDeclaredConstructors()[0];
		Method helloMethod = ReflectionUtils.findMethod(HelloService.class, "hello", String.class);

		hints.reflection()
				.registerConstructor(helloConstructor, ExecutableMode.INVOKE)
				.registerMethod(helloMethod, ExecutableMode.INVOKE);

	}

}
