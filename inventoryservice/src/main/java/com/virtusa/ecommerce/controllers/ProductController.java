package com.virtusa.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.models.Product;
import com.virtusa.ecommerce.services.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RefreshScope
@RequestMapping("/products")
public class ProductController {
	 @Autowired
		private ProductService productService;
	    
	 @PostMapping({"/v1.0", "/v1.1"})

	    @CrossOrigin("*")
	    public ResponseEntity<?> addProduct(@RequestBody Product product)
	    {
	    	
	    	Product productObj=productService.addProduct(product);
	    	if(productObj!=null)
	    	
	    	  return ResponseEntity.status(HttpStatus.ACCEPTED).body(productObj);
	    	else
	    		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Added");
	    	
	    }
	    
	 @GetMapping({"/v1.0", "/v1.1"})

	    @CrossOrigin("*")
	    public List<Product> findAllProducts()
	    {
	    	
	    		return productService.getAllProducts();
	    	
	    }
}
