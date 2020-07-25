package com.mindtree.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindtree.order.client.IProductClient;
import com.mindtree.order.util.ProductResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductCacheService {

	@Autowired
	private IProductClient iProductClient;

	@Cacheable(value = "products")
	public List<ProductResponse> getProducts() {
		
		log.info("Retrieving products-------");
		
		return iProductClient.getProducts();
	}

}
