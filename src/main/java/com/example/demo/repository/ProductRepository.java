package com.example.demo.repository;

import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Product;

@Configuration
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query(value = "select p from  Product p where p.name in ?1") 
	Product getProductByName(final String productName);
}