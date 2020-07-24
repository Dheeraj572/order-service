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

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository iOrderRepository;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void createOrder(List<OrderRequest> orderRequestList) {
		
		log.info("Mapping orderRequest list to order list");

		List<Order> orderList = orderRequestList.parallelStream().map(OrderService::convertToOrder)
				.collect(Collectors.toList());

		log.info("Mapped orderRequest list to order list");

		iOrderRepository.saveAll(orderList);

	}

	@Override
	public List<OrderResponse> getOrders() {

		List<Order> orderList = iOrderRepository.findAll();

		log.info("Mapping Order list to Order Response list");

		List<OrderResponse> orderResponseList = orderList.parallelStream().map(OrderService::convertToOrderResponse)
				.collect(Collectors.toList());

		log.info("Mapped Order list to Order Response list");

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
