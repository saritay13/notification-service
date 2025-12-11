package com.notification.task;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
	}

	@Test
	void testSave() throws Exception {
		mockMvc.perform(post("/tasks").
				contentType("application/json").
				content("{\"title\": \"My First Task\"}")).
				andExpect(status().isOk()).
				andExpect(jsonPath("$.title").value("My First Task"));
				//andExpect(jsonPath("$.status").value("CREATED"));
	}

	@Test
	void shouldGetAllTasks() throws Exception {
		mockMvc.perform(get("/tasks"))
				.andExpect(status().isOk());
	}



}
