package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;

	public Product saveProduct(ProductRequest productRequest) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setReview(productRequest.getReview());
		return repository.save(product);
	}

	public List<Product> saveProducts(List<Product> products) {
		return repository.saveAll(products);
	}

	public List<Product> getProducts() {
		return repository.findAll();
	}

	/*
	 * public Product getProductById(int id) throws Exception { Product product =
	 * null; try { product = repository.findById(id).orElse(null); if (product !=
	 * null) { return product; } else { throw new
	 * Exception("Requested resource with id" + id + "not found"); } } catch
	 * (Exception e) {
	 * 
	 * throw new Exception("Requested resource with id" + id + "not found"); }
	 * 
	 * }
	 */
	public Product getProductById(int id) throws ProductNotFoundException {
		Product existingProduct = repository.findById(id).orElse(null);
		if (existingProduct == null) {
			throw new ProductNotFoundException("Menthioned id " + id + "Not found");

		}
		return existingProduct;

	}

	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "product remove !! " + id;
	}

	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(product);
		existingProduct.setName(product.getName());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setReview(product.getReview());
		return repository.save(existingProduct);
	}

	public Product getProductByname(String name) throws ProductNotFoundException {
		Product existingProduct = repository.getProductByName(name);
		if (existingProduct == null) {
			throw new ProductNotFoundException("Menthioned name " + name + "Not found");
		
	
	}
		return existingProduct;
	}
}
	