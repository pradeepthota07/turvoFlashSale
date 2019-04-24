package com.turvo.flashsale.api.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.service.BaseFlashSaleUserService;

@Component
public class FlashSaleUserValidator {

	@Autowired
	private BaseFlashSaleUserService flashSaleUserService;

	public List<ExceptionResponse> validateUser(String userId) {
		List<ExceptionResponse> exceptionResponses = new ArrayList<>();
		ExceptionResponse response = new ExceptionResponse();

		if (userId != null && !("").equals(userId.trim())) {
			FlashSaleUserDTO flashSaleUserDTO;
			flashSaleUserDTO = flashSaleUserService.retrieveUser(userId);
			if (flashSaleUserDTO == null || flashSaleUserDTO.getUserId() == null || !flashSaleUserDTO.isRegistered()) {
				response.setCode("FSU-002");
				response.setMessage("User not registered");
				exceptionResponses.add(response);
			}
		} else {
			response.setCode("FSPU-001");
			response.setMessage("User Id is required");
			exceptionResponses.add(response);
		}
		return exceptionResponses;
	}
}
