package com.virtusa.ecommerce.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Entity
@Table(name="Location")
@Data
public class Location {
	@Id
	@Column(name="Regional_Code")
	private long regionalCode;
	@Column(name="Address",nullable = false,length = 150)
	private String address;
	@Column(name="Contact_Number")
	private long contactNumber;

}
