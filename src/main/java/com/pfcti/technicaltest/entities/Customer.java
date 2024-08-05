package com.pfcti.technicaltest.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * A customer representation of the table in the database
 * 
 * @author Jose Chavez
 * @version 0.0.1
 */
@Data
@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	private String id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "birthdate")
	private LocalDate birthdate;
}
