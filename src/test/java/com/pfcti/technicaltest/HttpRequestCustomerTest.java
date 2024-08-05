package com.pfcti.technicaltest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.pfcti.technicaltest.dtos.CustomerToUpdateDTO;
import com.pfcti.technicaltest.entities.Customer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HttpRequestCustomerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Test create a customer
	 */
	@Test
	@Order(1)
	void createCustomerTest() {
		Customer customer = new Customer();
		customer.setId("0");
		customer.setFirstName("NameTest");
		customer.setLastName("LastNameTest");
		customer.setPhone("phoneTest");
		customer.setBirthdate(LocalDate.now());

		Customer createdCustomer = restTemplate.postForObject("http://localhost:" + port + "/customers", customer,
				Customer.class);

		assertEquals(customer, createdCustomer);
	}

	/**
	 * Test responsible for retrieving a customer by ID
	 */
	@Test
	@Order(2)
	void getCustomerByIdTest() {
		String id = "0";

		Customer customer = restTemplate.getForObject("http://localhost:" + port + "/customers/" + id, Customer.class);

		assertEquals(id, customer.getId());
	}

	/**
	 * Test responsible for update the customer's attributes
	 */
	@Test
	@Order(2)
	void updateByIdTest() {
		String id = "0";

		CustomerToUpdateDTO customerToUpdateDTO = new CustomerToUpdateDTO();
		customerToUpdateDTO.setFirstName("NameTestUpdated");
		customerToUpdateDTO.setLastName("LastNameTestUpdated");
		customerToUpdateDTO.setPhone("phoneTestUpdated");
		customerToUpdateDTO.setBirthdate(LocalDate.now().minusDays(1));

		Customer customerBeforeUpdate = restTemplate.getForObject("http://localhost:" + port + "/customers/" + id,
				Customer.class);

		restTemplate.put("http://localhost:" + port + "/customers/" + id, customerToUpdateDTO);

		Customer customerAfterUpdate = restTemplate.getForObject("http://localhost:" + port + "/customers/" + id,
				Customer.class);

		assertNotEquals(customerBeforeUpdate.getFirstName(), customerAfterUpdate.getFirstName());
		assertNotEquals(customerBeforeUpdate.getLastName(), customerAfterUpdate.getLastName());
		assertNotEquals(customerBeforeUpdate.getPhone(), customerAfterUpdate.getPhone());
		assertNotEquals(customerBeforeUpdate.getBirthdate(), customerAfterUpdate.getBirthdate());

	}

	/**
	 * Test responsible for delete a customer by ID
	 */
	@Test
	@Order(3)
	void deleteCustomerByIdTest() {
		String id = "0";

		restTemplate.delete("http://localhost:" + port + "/customers/" + id);
		
		Customer customer = restTemplate.getForObject("http://localhost:" + port + "/customers/" + id, Customer.class);

		assertNull(customer.getId());
	}


}