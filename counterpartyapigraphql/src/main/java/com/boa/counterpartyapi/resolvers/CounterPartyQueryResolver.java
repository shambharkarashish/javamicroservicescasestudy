package com.boa.counterpartyapi.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boa.counterpartyapi.models.Customer;
import com.boa.counterpartyapi.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class CounterPartyQueryResolver implements GraphQLQueryResolver{
    @Autowired
	private CustomerService customerService;
	
	public List<Customer> findAllCustomers()
	{
		return this.customerService.fetchAllCustomers();
	}
	
	public Customer findCustomer(final Long customerId)
	{
		return this.customerService.findCustomerById(customerId);
	}
	
	//conditional querying
	
	
}
