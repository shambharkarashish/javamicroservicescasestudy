package com.boa.counterpartyapi.mutations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boa.counterpartyapi.models.Customer;
import com.boa.counterpartyapi.repositories.CustomerRepository;
import com.boa.counterpartyapi.services.CustomerService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
@Component
public class CounterPartyMutator implements GraphQLMutationResolver {
	@Autowired
	private CustomerService customerService;
   
    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }
  
    @Transactional
    public Customer updateCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }
    
    @Transactional
    public boolean deleteCustomer(Long id) {
        return customerService.deleteCustomerById(id);
    }
}
