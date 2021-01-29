package com.boa.counterpartyapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import org.springframework.stereotype.Service;
import com.boa.counterpartyapi.models.Customer;
import com.boa.counterpartyapi.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;
    
    //insert operation
	/*
	 * @Caching( put= { @CachePut(value= "customerCache", key=
	 * "#customer.customerId") }, evict= { @CacheEvict(value= "allCustomersCache",
	 * allEntries= true) } )
	 */

    public Customer saveCustomer(Customer customer)
    {
    	if(customer.getAddresses()!=null) {
    	if( customer.getAddresses().size() > 0 )
        {
            customer.getAddresses().stream().forEach( address -> {
                address.setCustomer(customer);
            } );
        }
    	}
    	return this.customerRepository.save(customer);
    }
    
    //findall
	/* @Cacheable(value= "allCustomersCache", unless= "#result.size() == 0") */
    public List<Customer> fetchAllCustomers()
    {
    	return this.customerRepository.findAll();
    }
    
    //find customer by Id
    @Cacheable(value= "customerCache", key= "#customerId")		
    public Customer findCustomerById(long customerId)
    {
    	return this.customerRepository.findById(customerId).orElse(null);
    }
    
    //delete customer by id
	/*
	 * @Caching( evict= {
	 * 
	 * @CacheEvict(value= "customerCache", key= "#customerId"),
	 * 
	 * @CacheEvict(value= "allCustomersCache", allEntries= true) } )
	 */

    public boolean deleteCustomerById(long customerId)
    {
    	boolean status=false;
    	this.customerRepository.deleteById(customerId);
    	Customer customer = findCustomerById(customerId);
    	if (customer==null)
    		status=true;
    	return status;
    }
    
	//update customer 
    //must pass customer id
	/*
	 * @Caching( put= { @CachePut(value= "customerCache", key=
	 * "#customer.customerId") }, evict= { @CacheEvict(value= "allCustomersCache",
	 * allEntries= true) })
	 */

    public Customer updateCustomer(Customer customer)
    {
    	return this.customerRepository.save(customer);
    }
    
}
