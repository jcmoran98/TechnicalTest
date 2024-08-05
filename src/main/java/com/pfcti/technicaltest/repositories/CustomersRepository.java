package com.pfcti.technicaltest.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.pfcti.technicaltest.entities.Customer;

/**
 * Repository to manage the customer entity
 * 
 * @author Jose Chavez
 * @version 0.0.1
 */
public interface CustomersRepository extends CrudRepository<Customer, String> {

	@Procedure("proc_InsertCustomer")
	void saveCustomer(String id, String firstName, String lastName, String phone, LocalDate birthdate);

	@Procedure("proc_GetCustomerById")
	Optional<Customer> getCustomerById(String customerId);

	@Procedure("proc_GetAllCustomers")
	List<Customer> getAllCustomers(boolean sortById, boolean sortByName, boolean sortByBirthdate);

	@Procedure("proc_UpdateCustomer")
	void updateCustomerById(String customerId, String firstName, String lastName,String phone, LocalDate birthdate);

	@Procedure("proc_DeleteCustomer")
	void deleteCustomerById(String customerId);
}
