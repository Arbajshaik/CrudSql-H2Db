package com.example.demo.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Product;

@Configuration
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}