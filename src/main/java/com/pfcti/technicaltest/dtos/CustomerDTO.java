package com.pfcti.technicaltest.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * A customer representation to receipt the information
 * 
 * @author Jose Chavez
 * @version 0.0.1
 */
@Data
public class CustomerDTO {

	@NotBlank(message = "The id field is required")
	@Size(max = 50, message = "The id field can't exceed 50 characters.")
	private String id;
	
	@NotBlank(message = "The firstName field is required")
	@Size(max = 20, message = "The firstName field can't exceed 20 characters")
	private String firstName;

	@NotBlank(message = "The lastName field is required")
	@Size(max = 20, message = "The lastName field can't exceed 20 characters")
	private String lastName;
	
	@NotBlank(message = "The phone field is required")
	@Size(max = 20, message = "The phone field can't exceed 20 characters")
	private String phone;
	
	@NotNull(message = "The birthdate field is required")
	private LocalDate birthdate;
}
