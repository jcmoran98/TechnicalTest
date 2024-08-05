package com.pfcti.technicaltest.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerToUpdateDTO {

	@Size(max = 20, message = "The firstName field can't exceed 20 characters")
	private String firstName;
	
	@Size(max = 20, message = "The lastName field can't exceed 20 characters")
	private String lastName;
	
	@Size(max = 20, message = "The phone field can't exceed 20 characters")
	private String phone;

	private LocalDate birthdate;
}
