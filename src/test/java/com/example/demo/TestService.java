package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.model.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrudSqlApplicationTests.class)
class TestService {
	@InjectMocks
	ProductService service;

	@Mock
	ProductRepository repo;

	@Test
	void getProducts() {
		List<Product> list = new ArrayList<Product>();
		Product idone = new Product();
		Product idtwo = new Product();
		Product idthree = new Product();
		list.add(idone);
		list.add(idtwo);
		list.add(idthree);
		when(service.getProducts()).thenReturn(list);
	}

	@Test
	void getProductById() throws Exception {
		Product product = new Product();
		product.setName("Phone");
		Optional<Product> productDb = Optional.ofNullable(product);
		when(repo.findById(1)).thenReturn(productDb);
		Product response = service.getProductById(1);
		assertEquals("Phone", product.getName());
	}

	@Test
	void saveProduct() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setName("Phone");
		Product response = service.saveProduct(productRequest);
		assertEquals("Phone", productRequest.getName());
	}

	@Test
	void deleteProduct() {
		Product product = new Product();
		product.setName("Phone");
		Optional<Product> productDb = Optional.ofNullable(product);
		when(repo.findById(1)).thenReturn(productDb);
		service.deleteProduct(12345);
		assertEquals("Phone", product.getName());
	}

}