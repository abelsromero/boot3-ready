package com.example.boot3ready;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {AppConfiguration.class, HelloController.class})
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnMessageFromAutowiredBean() throws Exception {
		final String source = "autowired";
		this.mockMvc.perform(get("/hello/me?source=" + source))
				.andExpect(status().isOk())
				.andExpect(jsonPath("text").value("Hello me"));
	}

	@Test
	void shouldReturnMessageFromAppContextBean() throws Exception {
		final String source = "context";
		this.mockMvc.perform(get("/hello/me?source=" + source))
				.andExpect(status().isOk())
				.andExpect(jsonPath("text").value("Hello me"));
	}

	@Test
	void shouldReturnMessageFromReflectiveInstance() throws Exception {
		final String source = "reflection";
		this.mockMvc.perform(get("/hello/me?source=" + source))
				.andExpect(status().isOk())
				.andExpect(jsonPath("text").value("Hello me"));
	}

	@Test
	void shouldReturnDefault() throws Exception {
		final String source = "some-random";
		this.mockMvc.perform(get("/hello/me?source=" + source))
				.andExpect(status().isOk())
				.andExpect(jsonPath("text").value("who?"));
	}
}
