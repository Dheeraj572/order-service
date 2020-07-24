package com.mindtree.order.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.mindtree.order.util.Product;

@FeignClient(value = "product", url = "http://localhost:4000")
public interface IProductClient {

	@GetMapping("products")
	List<Product> getProducts();
}
