package com.turvo.flashsale.api.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.exception.ExceptionResponse;

@Component
public class FlashSalePurchaseValidator {

	@Autowired
	private FlashSaleInventoryValidator inventoryValidator;

	@Autowired
	private FlashSaleUserValidator userValidator;

	public List<ExceptionResponse> validatePurchase(FlashSalePurchaseDTO purchaseDTO) {
		// Error codes and exceptions to be maintained in properties or DB
		List<ExceptionResponse> exceptionResponses = new ArrayList<>();
		if (purchaseDTO != null) {
			exceptionResponses.addAll(userValidator.validateUser(purchaseDTO.getUserId()));
			exceptionResponses.addAll(inventoryValidator.validateInventory(purchaseDTO.getInventoryId()));
		} else {
			ExceptionResponse response = new ExceptionResponse("FSP-001", "User and inventory are required");
			exceptionResponses.add(response);
		}
		return exceptionResponses;
	}
}
