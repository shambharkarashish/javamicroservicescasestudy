package com.virtusa.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.actuate.endpoint.annotation.EndpointConverter;

import lombok.Data;

@Entity
@Table(name="Product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Product_Id")
	private  long productId;
    @Column(name="Product_Name",length = 50,nullable = false)
	private  String name;
    @Column(name="Buffer_Level")
	private  int bufferLevel;
    @Enumerated(EnumType.STRING)
    @Column(name="Product_Type")
    private ProductType productType;
  
}
