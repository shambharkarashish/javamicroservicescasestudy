package com.boa.counterpartyapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boa.counterpartyapi.models.Customer;
import com.boa.counterpartyapi.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;

@RestController
public class CustomerController {
    @Autowired
	private CustomerService customerService;
    private static final Logger logger = LogManager.getLogger(CustomerController.class);
   // private static final Logger logger = LogManager.getRootLogger();
    
    //customers post
    @CrossOrigin("*")
    @PostMapping("/customers")
    @ResponseBody
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer)
    {
    	

    	Customer customerObj=this.customerService.saveCustomer(customer);
    	if(customerObj==null)
    	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).
    				body("Customer Json not Valid or DB Connection error");
    	
    	else
    		return  ResponseEntity.status(HttpStatus.ACCEPTED).
    				body(customerObj);
    	
    }
    
    @CrossOrigin("*")
    @GetMapping("/customers")
    public List<Customer> getAllCustomers()
    {
    	 //if (logger.isDebugEnabled()) {
            logger.info("From Log4j 2 - data : {}");
        // }

         // java 8 lambda, no need to check log level
         logger.info("From Log4j 2 - Data Lambda : {}");
    	return this.customerService.fetchAllCustomers();
    	
    }
    
    @CrossOrigin("*")
    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") long customerId)
    {
    	return this.customerService.findCustomerById(customerId);
    	
    }
    
    @CrossOrigin("*")
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") long customerId)
    {
    	 boolean status=this.customerService.deleteCustomerById(customerId);
    	 if(status)
    		 return ResponseEntity.status(HttpStatus.ACCEPTED)
    				 .body("Customer Instance-->"+customerId+"Deleted");
    	 else
    		 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
    				 .body("Customer Instance-->"+customerId+"could not be deleted");
    	 
    	
    }
    
    @CrossOrigin("*")
    @PutMapping("/customers") //existing customer id mandatory
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer)
    {
    	Customer customerObj=this.customerService.saveCustomer(customer);
    	if(customerObj==null)
    	 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).
    				body("Customer Json not Valid or DB Connection error");
    	
    	else
    		return  ResponseEntity.status(HttpStatus.ACCEPTED).
    				body(customerObj);
    	
    	
    }
    
    //squiggly filter
    //http://localhost:7070/customers/filters/1?fields=customerId,name.firstName
    //http://localhost:7070/customers/filters/1?fields=-customerId,-name,-addresses
    @CrossOrigin("*")
	@GetMapping("/customers/filters/{customerId}")
	public ResponseEntity<?> findCustomerById(@RequestParam(name = "fields", required = false) 
    String fields, @PathVariable("customerId") long customerId)
	{
    	
    	Map<Object,Object> model=new HashMap<>();
    	
    	Customer customer = this.customerService.findCustomerById(customerId);
    	
    	if(customer!=null)
    	{
    		//fields refers to runtime selection
    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
			return ResponseEntity.ok(SquigglyUtils.stringify(mapper, customer));

    	}
    	else
    	{
	         model.put("message", "customer not existing");
		        
	         return ResponseEntity.ok(model);
    	}

	}
    
    
	
}
