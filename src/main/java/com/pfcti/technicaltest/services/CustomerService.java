package com.pfcti.technicaltest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfcti.technicaltest.dtos.CustomerDTO;
import com.pfcti.technicaltest.dtos.CustomerToUpdateDTO;
import com.pfcti.technicaltest.entities.Customer;
import com.pfcti.technicaltest.exceptions.DuplicateEntityException;
import com.pfcti.technicaltest.exceptions.EntityNotFoundException;
import com.pfcti.technicaltest.repositories.CustomersRepository;

/**
 * Service to manage the bussines logic of customer entity
 * 
 * @author Jose Chavez
 * @version 0.0.1
 */
@Service
public class CustomerService {

	private static final String CUSTOMER_DOES_NOT_EXIST_ERROR_MESSAGE = "The customer id provided doesn't exist";

	private static final String CUSTOMER_ID_ALREADY_EXISTS_ERROR_MESSAGE = "The customer id already exists.";
	
	@Autowired
	private CustomersRepository customersRepository;

	
	/**
	 * Method that create a customer with the input attributes
	 * 
	 * @param customerDTO
	 * @return Optional<Customer>       The information of the created client
	 * @throws DuplicateEntityException In case of duplicate id
	 */
	@Transactional(rollbackForClassName = "Exception")
	public Optional<Customer> createCustomer(CustomerDTO customerDTO) throws DuplicateEntityException {
		
		if (customersRepository.existsById(customerDTO.getId())) {
			throw new DuplicateEntityException(CUSTOMER_ID_ALREADY_EXISTS_ERROR_MESSAGE);
		}

		customersRepository.saveCustomer(customerDTO.getId(), customerDTO.getFirstName(), customerDTO.getLastName(),
				customerDTO.getPhone(), customerDTO.getBirthdate());

		return customersRepository.getCustomerById(customerDTO.getId());
	}

	/**
	 * Method that update a customer with the input attributes
	 * 
	 * @param customerId
	 * @param customerToUpdateDTO
	 * @return Optional<Customer>		The information of the updated client
	 * @throws EntityNotFoundException  In case of not found the id
	 */
	@Transactional(rollbackForClassName = "Exception")
	public Optional<Customer> updateCustomer(String customerId, CustomerToUpdateDTO customerToUpdateDTO) throws EntityNotFoundException {
		
		if (!customersRepository.existsById(customerId)) {
			throw new EntityNotFoundException(CUSTOMER_DOES_NOT_EXIST_ERROR_MESSAGE);
		}

		customersRepository.updateCustomerById(customerId, customerToUpdateDTO.getFirstName(),
				customerToUpdateDTO.getLastName(), customerToUpdateDTO.getPhone(), customerToUpdateDTO.getBirthdate());

		return customersRepository.getCustomerById(customerId);
	}

	/**
	 * Method that delete a customer by the input id
	 * 
	 * @param customerId
	 * @return Optional<Customer>		The information of the deleted client
	 * @throws EntityNotFoundException	In case of not found the id
	 */
	@Transactional(rollbackForClassName = "Exception")
	public Optional<Customer> deleteCustomer(String customerId) throws EntityNotFoundException {

		Optional<Customer> customer = customersRepository.getCustomerById(customerId);

		if (customer.isEmpty()) {
			throw new EntityNotFoundException(CUSTOMER_DOES_NOT_EXIST_ERROR_MESSAGE);
		}
		
		customersRepository.deleteCustomerById(customerId);

		return customer;
	}

	/**
	 *  Method that get a customer information by the input id
	 * 
	 * @param customerId
	 * @return Optional<Customer>		The information of the client
	 * @throws EntityNotFoundException	In case of not found the id
	 */
	@Transactional(rollbackForClassName = "Exception")
	public Optional<Customer> findCustomerById(String customerId) throws EntityNotFoundException {
		
		if (!customersRepository.existsById(customerId)) {
			throw new EntityNotFoundException(CUSTOMER_DOES_NOT_EXIST_ERROR_MESSAGE);
		}

		return customersRepository.getCustomerById(customerId);
	}

	/**
	 * Method that get all customers information
	 * 
	 * 
	 * @param sortByIdAsc
	 * @param sortByNameAsc
	 * @param sortByBirthdateDesc
	 * @return List<Customer>
	 */
	@Transactional(rollbackForClassName = "Exception")
	public List<Customer> findAllCustomer(boolean sortByIdAsc, boolean sortByNameAsc, boolean sortByBirthdateDesc) {

		return customersRepository.getAllCustomers(sortByIdAsc, sortByNameAsc, sortByBirthdateDesc);

	}
}
