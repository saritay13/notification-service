package com.notification.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
	}

	@Test
	void sendMessage_shouldReturnOk() throws Exception {
		String message = "my first message";
		mockMvc.perform(post("/api/messages").
				contentType(MediaType.TEXT_PLAIN).
				content(message)).
				andExpect(status().isOk()).
				andExpect(content().string(("Message Sent: "  +message)));
	}



}
