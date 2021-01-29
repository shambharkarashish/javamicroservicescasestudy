package com.boa.counterpartyapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Entity
@Table(name="BOA_Address")
@Data
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Address_Id")
	private long addressId;
	@Column(name="Door_No",nullable = false, length = 5)
	private String doorNo;
	@Column(name="Street",nullable = false, length = 150)
	private String street;
	@Column(name="City", nullable = false, length = 150)
	private String city;
	@Column(name="State", nullable = false, length = 150)
	private String state;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "Customer_Id"), name = "Customer_Id")
	@JsonIgnore
	private Customer customer;
	

}
