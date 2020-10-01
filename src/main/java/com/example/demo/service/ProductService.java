package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductRequest;
import com.example.demo.entity.Product;
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

	public Product getProductById(int id) throws Exception {
		Product product = null;
		try {
			product = repository.findById(id).orElse(null);
			if (product != null) {
				return product;
			} else {
				throw new Exception("Requested resource with id" + id + "not found");
			}
		} catch (Exception e) {

			System.out.println("Exception Occured" + e);
		}
		return product;
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

	public Product getProductByname(String name) {
		return repository.findAll().stream().filter(findname -> findname.getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}

}