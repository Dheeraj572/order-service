package com.mindtree.order.util;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {

	private Long id;
	private String customerName;
	private Date orderDate;
	private String shippingAddress;
	//private List<OrderItem> orderItems;
}
