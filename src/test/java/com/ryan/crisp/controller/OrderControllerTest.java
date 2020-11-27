package com.ryan.crisp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.ryan.crisp.service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private OrderService service;
	
	@Test
	public void testTransformSuccess() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
	        "file", 
	        "hello.txt", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Hello, World!".getBytes()
	      );
		doNothing().when(service).transform(Mockito.any());
		this.mockMvc.perform(multipart("/order/transform").file(file)).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testTransformFail() throws Exception {
		MockMultipartFile file = new MockMultipartFile(
	        "file", 
	        "hello.txt", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Hello, World!".getBytes()
	      );
		doThrow(IOException.class).when(service).transform(Mockito.any());
		this.mockMvc.perform(multipart("/order/transform").file(file)).andDo(print()).andExpect(status().isBadRequest());
	}
}
