package com.mindtree.order.util;

import java.util.List;

import javax.validation.constraints.NotNull;

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
public class OrderRequest {

	@NotNull
	private String customerName;
	@NotNull
	private String shippingAddress;
	@NotNull
	private List<OrderItemRequest> orderItemList;
}
