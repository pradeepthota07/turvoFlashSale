package com.turvo.flashsale.api.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.service.FlashSaleInventoryService;
import com.turvo.flashsale.api.service.FlashSaleUserService;

@Component
public class FlashSalePurchaseValidator {

	@Autowired
	private FlashSaleUserService flashSaleUserService;

	@Autowired
	private FlashSaleInventoryService flashSaleInventoryService;

	public List<ExceptionResponse> validatePurchase(FlashSalePurchaseDTO purchaseDTO, Date currentTime) {
		// Error codes and exceptions to be maintained in properties or DB
		List<ExceptionResponse> exceptionResponses = new ArrayList<>();
		ExceptionResponse response = new ExceptionResponse();

		if (purchaseDTO != null) {
			if (purchaseDTO.getUserId() != null) {
				FlashSaleUserDTO flashSaleUserDTO;
				try {
					flashSaleUserDTO = flashSaleUserService.getUser(purchaseDTO.getUserId());
					if (flashSaleUserDTO == null || flashSaleUserDTO.getUserId() == null || !flashSaleUserDTO.isRegistered()) {
						response.setCode("FSP-002");
						response.setMessage("User not registered");
						exceptionResponses.add(response);
					}
				} catch (FlashSaleException e) {
					response = e.getExceptionResponse();
					exceptionResponses.add(response);
				}
			} else {
				response.setCode("FSP-001");
				response.setMessage("User Id is required");
				exceptionResponses.add(response);
			}

			if (purchaseDTO.getInventoryId() > 0) {
				FlashSaleInventoryDTO flashSaleInventoryDTO;
				try {
					flashSaleInventoryDTO = flashSaleInventoryService.getInventory(purchaseDTO.getInventoryId());
					if (flashSaleInventoryDTO == null) {
						response.setCode("FSP-004");
						response.setMessage("Inventory not found");
						exceptionResponses.add(response);
					} else {
						if (flashSaleInventoryDTO.getQuantity() <= 0) {
							response.setCode("FSP-005");
							response.setMessage("Inventory not available");
							exceptionResponses.add(response);
						}
					}
					
					if(flashSaleInventoryDTO.getStartDate()!=null && flashSaleInventoryDTO.getEndDate()!=null) {
						if(currentTime.compareTo(flashSaleInventoryDTO.getStartDate())<0 || currentTime.compareTo(flashSaleInventoryDTO.getEndDate())>0) {
							response.setCode("FSP-006");
							response.setMessage("Flash sale is yet to start or is already completed");
							exceptionResponses.add(response);
						}
					}
				} catch (FlashSaleException e) {
					response = e.getExceptionResponse();
					exceptionResponses.add(response);
				}
			} else {
				response.setCode("FSP-003");
				response.setMessage("Invalid Inventory");
				exceptionResponses.add(response);
			}
			
		}

		return exceptionResponses;
	}
}
