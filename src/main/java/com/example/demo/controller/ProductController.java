package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.ProductService;

@RequestMapping("/productcategory")
@RestController
@Validated
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/product")
	public Product addProduct(@Valid @RequestBody ProductRequest productRequest) {
		return service.saveProduct(productRequest);
	}

	@GetMapping("/products")
	public List<Product> findAllProducts() {
		return service.getProducts();
	}

	@GetMapping("/product/{id}")
	public Product findProductById(@PathVariable int id) throws  ProductNotFoundException {
		return service.getProductById(id);
	}

	@GetMapping("/Product/{name}")
	public Product findProductByname(@PathVariable String name) throws ProductNotFoundException {
		return service.getProductByname(name);
	}

	@PutMapping("/product")
	public Product updatProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}

	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
}