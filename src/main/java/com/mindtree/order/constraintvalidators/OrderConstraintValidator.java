package com.mindtree.order.constraintvalidators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mindtree.order.util.OrderRequest;

public class OrderConstraintValidator implements ConstraintValidator<ValidOrder, List<OrderRequest>> {


	@Override
	public boolean isValid(List<OrderRequest> orderRequestList, ConstraintValidatorContext context) {
		
		boolean isValid = true;

		for (OrderRequest orderRequest : orderRequestList) {
			
			if (!orderRequest.getCustomerName().matches("^[a-zA-Z0-9äöüÄÖÜ]*$")
					|| orderRequest.getCustomerName().isEmpty()) {
				isValid = false;
			}
		}

		return isValid;
	}

}
