package com.mindtree.order.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.order.entity.Order;
import com.mindtree.order.repository.IOrderRepository;
import com.mindtree.order.util.OrderRequest;
import com.mindtree.order.util.OrderResponse;


@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository iOrderRepository;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void createOrder(List<OrderRequest> orderRequestList) {

		List<Order> orderList = orderRequestList.parallelStream().map(OrderService::convertToOrder)
				.collect(Collectors.toList());

		iOrderRepository.saveAll(orderList);

	}

	@Override
	public List<OrderResponse> getOrders() {

		List<Order> orderList = iOrderRepository.findAll();

		List<OrderResponse> orderResponseList = orderList.parallelStream().map(OrderService::convertToOrderResponse)
				.collect(Collectors.toList());

		return orderResponseList;
	}

	private static Order convertToOrder(OrderRequest orderRequest) {

		Order order = objectMapper.convertValue(orderRequest, Order.class);
		order.setOrderDate(new Date());
		return order;
	}

	private static OrderResponse convertToOrderResponse(Order order) {

		return objectMapper.convertValue(order, OrderResponse.class);
	}

}
