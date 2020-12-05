package com.virtusa.banking.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="Customer")
@Data
@XmlRootElement
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Customer_Id")
	private long customerId;
	@Embedded
	private FullName name;
	@Enumerated(EnumType.STRING)
	@Column(name="Gender")
	private Gender gender;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="DOB")
	private LocalDate dob;
	@Column(name="Email",nullable = false,length = 150)
	@ApiModelProperty(example = "sample@gmail.com")	
	@Email
	@NotBlank(message = "Email is mandatory")
	private String email;
	@Column(name="Mobile_No")
	@ApiModelProperty(example = "111111111")		
	private long mobileNo;	 

}
