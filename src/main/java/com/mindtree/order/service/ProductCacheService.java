package com.mindtree.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindtree.order.client.IProductClient;
import com.mindtree.order.util.ProductResponse;

@Service
public class ProductCacheService {

	@Autowired
	private IProductClient iProductClient;

	@Cacheable(value = "products")
	public List<ProductResponse> getProducts() {

		return iProductClient.getProducts();
	}

}
