package com.boa.counterpartyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boa.counterpartyapi.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
