package com.example.boot3ready;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("https://github.com/spring-projects/spring-boot/issues/32848")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerWithRestTemplateTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldReturnMessageFromAutowiredBean() {
		final String source = "autowired";
		String url = "/hello/me?source=" + source;

		var response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getBody())
				.contains("Hello me");
	}

	@Test
	void shouldReturnMessageFromAppContextBean() {
		final String source = "context";
		String url = "/hello/me?source=" + source;

		var response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getBody())
				.contains("Hello me");
	}

	@Test
	void shouldReturnMessageFromReflectiveInstance() {
		final String source = "reflection";
		String url = "/hello/me?source=" + source;

		var response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getBody())
				.contains("Hello me");
	}

	@Test
	void shouldReturnDefault() {
		final String source = "some-random";
		String url = "/hello/me?source=" + source;

		var response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getBody())
				.contains("who?");
	}
}
