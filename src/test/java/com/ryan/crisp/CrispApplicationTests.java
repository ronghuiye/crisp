package com.ryan.crisp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryan.crisp.controller.OrderController;

@SpringBootTest
class CrispApplicationTests {
	
	@Autowired
	private OrderController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
