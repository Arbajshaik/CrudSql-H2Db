package com.example.demo;

import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.controller.ProductController;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrudSqlApplicationTests.class)
class TestController {

	private MockMvc mockMvc;
	@InjectMocks
	ProductController controller;

	@Mock
	ProductService service;

	private static final MediaType APPLICATION_JSON_VALUE = null;
	@Autowired
	private WebApplicationContext wc;

	List<Product> products = new ArrayList<Product>();
	ObjectMapper mAPPER = new ObjectMapper();
	protected MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void getProducts() throws Exception {
		String uri="/productcategory/products";
		MvcResult result=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	public void saveProduct() throws Exception {
		String uri = "/productcategory/product";
		Product product = new Product();
		product.setId(1);
		product.setName("java");
		product.setPrice(6000);
		product.setQuantity(12);
		product.setReview(4);
		String inputJson = mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	public void updateProduct() throws Exception {
		String uri = "/productcategory/product";
		Product product = new Product();
		product.setId(1);
		product.setName("java");
		product.setPrice(20000);
		product.setQuantity(3);
		product.setReview(4);
		String inputJson = mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	public void deleteProduct() throws Exception {
		Product product = new Product ();
		String uri = "/productcategory/product";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}

}