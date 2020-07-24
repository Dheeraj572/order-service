package com.mindtree.order.service;

import java.util.List;

import com.mindtree.order.util.OrderRequest;
import com.mindtree.order.util.OrderResponse;

public interface IOrderService {

	List<OrderResponse> getOrders();
	void createOrder(List<OrderRequest> orderRequestList);
}
