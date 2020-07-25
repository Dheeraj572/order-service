package com.mindtree.order.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.order.constraintvalidators.ValidOrder;
import com.mindtree.order.service.IOrderService;
import com.mindtree.order.service.ProductCacheService;
import com.mindtree.order.userexception.ProductNotFound;
import com.mindtree.order.util.ProductRequest;
import com.mindtree.order.util.OrderRequest;
import com.mindtree.order.util.OrderResponse;
import com.mindtree.order.util.ProductResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("orders")
@CrossOrigin
@Log4j2
@Validated
public class OrderController {

	@Autowired
	private IOrderService iOrderService;

	@Autowired
	private ProductCacheService productCacheService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Create an order")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Order Created"),
			@ApiResponse(code = 400, message = "Invalid order") })
	@PostMapping
	public void createOrder(@RequestBody @ValidOrder List<OrderRequest> orderRequestList) throws Exception {

		List<ProductResponse> productList = loadProducts();
		isProductAvailable(orderRequestList, productList);

		log.info("Creating orders----");

		iOrderService.createOrder(orderRequestList);

		log.info("Created orders----");

	}

	@ApiOperation(value = "Get all orders")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retreieved all orders", response = OrderResponse.class, responseContainer = "List"),
			@ApiResponse(code = 204, message = "No Content") })
	@GetMapping
	public ResponseEntity<?> getOrders() {

		log.info("Retrieving orders------");

		List<OrderResponse> orderResponseList = iOrderService.getOrders();
		Optional<List<OrderResponse>> orderListOptional = Optional.ofNullable(orderResponseList);

		if (orderListOptional.isPresent() && orderListOptional.get().isEmpty()) {

			log.info("No Content------");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		log.info("Retrieved orders------");

		return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
	}

	//To load all products from order-item-service
	private List<ProductResponse> loadProducts() {

		return productCacheService.getProducts();
	}

	//To check if selected products list is available in product table
	private void isProductAvailable(List<OrderRequest> orderRequestList, List<ProductResponse> productList) {

		log.info("Checking if the selected products are available-----");
		
		for (OrderRequest orderRequest : orderRequestList) {
			List<ProductRequest> orderItemList = orderRequest.getOrderItemList();

			long count = orderItemList.parallelStream()
					.filter(orderItem -> productList.parallelStream()
							.anyMatch(product -> product.getProductName().equals(orderItem.getProductName())
									&& orderItem.getQuantity() <= product.getAvailableQuantity()))
					.count();

			if (count != orderItemList.size()) {
				throw new ProductNotFound("Product not found");
			}
		}

	}
}
