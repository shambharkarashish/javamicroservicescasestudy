package com.virtusa.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
