package com.turvo.flashsale.api.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.service.BaseFlashSaleInventoryService;

@Component
public class FlashSaleInventoryValidator {

	@Autowired
	private BaseFlashSaleInventoryService flashSaleInventoryService;

	public List<ExceptionResponse> validateInventory(int inventoryId) {
		List<ExceptionResponse> exceptionResponses = new ArrayList<>();
		ExceptionResponse response = new ExceptionResponse();
		FlashSaleInventoryDTO inventoryDTO;
		inventoryDTO = flashSaleInventoryService.getInventory(inventoryId);
		if (inventoryDTO == null) {
			response.setCode("FSI-001");
			response.setMessage("Inventory not found");
			exceptionResponses.add(response);
		} else {
			if (inventoryDTO.getQuantity() <= 0) {
				response.setCode("FSI-002");
				response.setMessage("Inventory not available");
				exceptionResponses.add(response);
			}

			if (isDateValid(inventoryDTO.getStartDate(), inventoryDTO.getEndDate())) {
				response.setCode("FSI-003");
				response.setMessage("Flash sale is yet to start/already completed");
				exceptionResponses.add(response);
			}
		}
		return exceptionResponses;
	}

	private static boolean isDateValid(Date startDate, Date endDate) {
		Date currentTime = new Date();
		if (startDate != null && endDate != null) {
			return currentTime.compareTo(startDate) < 0 || currentTime.compareTo(endDate) > 0;
		} else {
			return false;
		}
	}
}
