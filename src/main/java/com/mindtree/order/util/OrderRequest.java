package com.mindtree.order.util;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {

	@NotNull
	private String customerName;
	@NotNull
	private String shippingAddress;
	@NotNull
	private List<ProductRequest> orderItemList;
}
