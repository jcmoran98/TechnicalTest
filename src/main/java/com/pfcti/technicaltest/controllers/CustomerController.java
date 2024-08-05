package com.pfcti.technicaltest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pfcti.technicaltest.dtos.CustomerDTO;
import com.pfcti.technicaltest.dtos.CustomerToUpdateDTO;
import com.pfcti.technicaltest.entities.Customer;
import com.pfcti.technicaltest.exceptions.DuplicateEntityException;
import com.pfcti.technicaltest.exceptions.EntityNotFoundException;
import com.pfcti.technicaltest.exceptions.models.ErrorResponseModel;
import com.pfcti.technicaltest.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * Controller class to management the customer REST
 * 
 * 
 * @author Jose Chavez
 * @version 0.0.1
 */
@RestController
@RequestMapping("/customers")
@Tag(name = "Customer API")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private static final String FATAL_ERROR_MESSAGE = "An unexpected error occurred within the server.";

	/**
	 * POST method to create a customer
	 * 
	 * @param customerDTO
	 * @return Optional<Customer>
	 */
	@PostMapping
	@Operation(summary = "Create customer", description = "Create and retrieve customer")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer successfully created"),
			@ApiResponse(responseCode = "404", description = "The customer id provided doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))),
			@ApiResponse(responseCode = "500", description = "An unexpected error occurred within the server.", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))) 
	})
	public Optional<Customer> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {

		try {

			return customerService.createCustomer(customerDTO);

		} catch (DuplicateEntityException e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FATAL_ERROR_MESSAGE);

		}

	}

	/**
	 * PUT method that update a customer by id
	 * 
	 * @param customerId
	 * @param customerToUpdateDTO
	 * @return Optional<Customer>
	 */
	@PutMapping("/{customerId}")
	@Operation(summary = "Update customer by id", description = "Update and retrieve customer by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer successfully updated"),
			@ApiResponse(responseCode = "404", description = "The customer id provided doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))),
			@ApiResponse(responseCode = "500", description = "An unexpected error occurred within the server.", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class)) ) })
	public Optional<Customer> updateCustomer(@PathVariable("customerId") String customerId,
			@Valid @RequestBody CustomerToUpdateDTO customerToUpdateDTO) {

		try {

			return customerService.updateCustomer(customerId, customerToUpdateDTO);

		} catch (EntityNotFoundException e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FATAL_ERROR_MESSAGE);

		}
	}

	/**
	 * DELETE method that delete a customer by id
	 * 
	 * @param customerId
	 * @return Optional<Customer>
	 */
	@DeleteMapping("/{customerId}")
	@Operation(summary = "Delete customer by id", description = "Delete and retrieve customer by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer successfully deleted"),
			@ApiResponse(responseCode = "404", description = "The customer id provided doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))),
			@ApiResponse(responseCode = "500", description = "An unexpected error occurred within the server.", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))) })
	public Optional<Customer> deleteCustomer(@PathVariable("customerId") String customerId) {

		try {

			return customerService.deleteCustomer(customerId);

		} catch (EntityNotFoundException e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FATAL_ERROR_MESSAGE);

		}
	}

	/**
	 * GET method that get a customer by id
	 * 
	 * @param customerId
	 * @return Optional<Customer>
	 */
	@GetMapping("/{customerId}")
	@Operation(summary = "Get customer by id", description = "Retrieve customer details by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer successfully returned"),
			@ApiResponse(responseCode = "404", description = "The customer id provided doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))),
			@ApiResponse(responseCode = "500", description = "An unexpected error occurred within the server.", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))) })
	public Optional<Customer> getOneCustomerById(@PathVariable("customerId") String customerId) {

		try {

			return customerService.findCustomerById(customerId);

		} catch (EntityNotFoundException e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FATAL_ERROR_MESSAGE);

		}
	}

	/**
	 * GET method that get all customer with order filters
	 * 
	 * @param sortById
	 * @param sortByName
	 * @param sortByBirthdate
	 * @return List<Customer>
	 */
	@GetMapping
	@Operation(summary = "Get all customers", description = "Retrieve all customers with order params")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Customer successfully returned all"),
			@ApiResponse(responseCode = "500", description = "An unexpected error occurred within the server.", content = @Content(schema = @Schema(implementation = ErrorResponseModel.class))) })
	public List<Customer> getAllCustomer(
			@RequestParam(value = "sortByIdAsc", defaultValue = "false") boolean sortByIdAsc,
			@RequestParam(value = "sortByNameAsc", defaultValue = "false") boolean sortByNameAsc,
			@RequestParam(value = "sortByBirthdateDesc", defaultValue = "false") boolean sortByBirthdateDesc) {

		try {

			return customerService.findAllCustomer(sortByIdAsc, sortByNameAsc, sortByBirthdateDesc);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, FATAL_ERROR_MESSAGE);

		}
	}

	/**
	 * Method that handle the MethodArgumentNotValidException of the @Valid
	 * annotation
	 * 
	 * @param ex
	 * @return Map<String, String> Map with the information of error in validations
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField() + "Message";
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
