package com.pfcti.technicaltest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pfcti.technicaltest.controllers.CustomerController;

@SpringBootTest
class TechnicalTestApplicationTests {
	
	@Autowired
	private CustomerController customerController;

	@Test
	void contextLoads()throws Exception {
		assertThat(customerController).isNotNull();
	}
	

}
