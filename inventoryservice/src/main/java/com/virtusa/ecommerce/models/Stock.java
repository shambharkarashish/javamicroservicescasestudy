package com.virtusa.ecommerce.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Table(name="Stock")
@Data
public class Stock {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Invoice_No")
	private long invoiceNo;
	@Column(name="Cost")
	private long cost;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="DOP")
	private LocalDate dop;
	@Column(name="Qty")
	private long qty;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "Product_Id"), name = "Product_Id")
	private Product product;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "Location_Id"), name = "Location_Id")
	private Location location;
	
	
	
}
