package com.virtusa.banking.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.virtusa.banking.exceptions.RecordNotFoundException;
import com.virtusa.banking.models.Customer;
import com.virtusa.banking.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {
    @Autowired
	private CustomerService customerService;
    @Value("abc")
    private String message;
    //postmapping
    @RequestMapping(value = "/customers", 
  		  produces = { "application/json", "application/xml" }, 
  		  method = RequestMethod.POST)
    @CrossOrigin("*")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer)
    {
    	
    	Customer customerObj=customerService.addCustomer(customer);
    	
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
    	
    	
    }
    
    @RequestMapping(value = "/customers", 
    		  produces = { "application/json", "application/xml" }, 
    		  method = RequestMethod.GET)

    @CrossOrigin("*")
    public List<Customer> findAllCustomers()
    {
    	log.info("Message from Property file"+message);
    		return customerService.getAllCustomers();
    	
    }
    
    @GetMapping("/customers/{customerId}")
    @CrossOrigin("*")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") long customerId)
    {
    	Customer customer=customerService.getCustomerById(customerId);
    	
    	  if(customer == null) {
    	         throw new RecordNotFoundException("Invalid customer id : " + customerId);
    	    }
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
    	
    }
    
    @PutMapping("/customers")
    @CrossOrigin("*")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer)
    {
    	Customer customerObj=customerService.updateCustomer(customer);
    	if(customerObj!=null)
    	{
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerObj);
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Updated");
    	
    }
    
    @DeleteMapping("/customers/{customerId}")
    @CrossOrigin("*")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long customerId)
    {
    	boolean status=customerService.deleteCustomer(customerId);
    	if(status)
    	{
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Customer Deleted");
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Record Not Deleted");
    	
    }
    
    
    @GetMapping("/customers/gender")
    @CrossOrigin("*")
    public ResponseEntity<?> getCustomerByGender(@RequestParam(name = "genderValue", required = false) String genderValue)
    {
    	List<Customer> customers=customerService.getCustomerByGender(genderValue);
    	if(customers.size()>0)
    	{
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customers);
    	}
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Record Not Found");
    	
    }
    
    //squiggly
    //http://localhost:7070/customers/filters/2?fields=name,mobileNo
    @CrossOrigin("*")
	@GetMapping({"customers/filters/{customerId}"})
	public ResponseEntity<?> getCustomerByFilter(@RequestParam(name = "fields", required = false) 
    String fields, @PathVariable("customerId") long customerId)
	{
    	
    	Map<Object,Object> model=new HashMap<>();
    	
    	Customer customer= this.customerService.getCustomerById(customerId);
    	
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
