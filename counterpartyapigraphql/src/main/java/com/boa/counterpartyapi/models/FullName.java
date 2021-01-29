package com.boa.counterpartyapi.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullName {

	@Column(name="First_Name",nullable = false, length = 50)
	@NotBlank(message = "First Name is mandatory")
	@Size(min=5, message="First Name should have atleast 5 characters")
	private String firstName;
	@Column(name="Last_Name",nullable = false, length = 50)
	@NotBlank(message = "Last Name is mandatory")
	@Size(min=5, message="Last Name should have atleast 5 characters")
	private String lastName;
	@Column(name="Middle_Name", length = 50)	
	private String middleName;
}
