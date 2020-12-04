package com.virtusa.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.ecommerce.models.Stock;

public interface StockRepository extends JpaRepository<Stock,Long> {

}
