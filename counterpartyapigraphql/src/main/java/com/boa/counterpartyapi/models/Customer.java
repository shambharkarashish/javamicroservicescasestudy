package com.boa.counterpartyapi.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Entity
@Table(name="BOA_Customer")
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CustomerId")
	private long customerId;
	@Embedded	
	@Valid
	private FullName name;
	@Column(name="Email",nullable = false, length = 150)
	@NotBlank(message = "Email is mandatory")
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(name="Gender")	
	private Gender gender;
	//@JsonDeserialize(using = LocalDateDeserializer.class)
	//@JsonSerialize(using = LocalDateSerializer.class)
	//@DateTimeFormat(iso = ISO.DATE)
	//JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="DOB")
	private String dob;
	@OneToMany(mappedBy ="customer", cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
	@JsonProperty("addresses")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)	
	private List<Address> addresses;
	

}
