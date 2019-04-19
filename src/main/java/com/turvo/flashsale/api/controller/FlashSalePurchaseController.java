package com.turvo.flashsale.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.flashsale.api.common.ResponseFormat;
import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.exception.FlashSaleExceptionList;
import com.turvo.flashsale.api.service.FlashSalePurchaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
public class FlashSalePurchaseController {

	@Autowired
	private FlashSalePurchaseService flashSalePurchaseService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/purchase")
	@ApiOperation(httpMethod = "POST", value = "Purchase flash sale products", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Purchase", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity purchaseOrder(@RequestBody FlashSalePurchaseDTO flashSalePurchaseDTO) {

		ResponseFormat<FlashSalePurchaseDTO> response = new ResponseFormat<>();
		
		if (flashSalePurchaseDTO != null) {
			try {
				flashSalePurchaseDTO = flashSalePurchaseService.purchaseOrder(flashSalePurchaseDTO);
				response.setData(flashSalePurchaseDTO);
			} catch (FlashSaleExceptionList e) {
				List<ExceptionResponse> exceptionResponses = e.getExceptionResponses();
				response.setErrors(exceptionResponses);
				return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
			} catch (FlashSaleException e) {
				List<ExceptionResponse> exceptionResponses = new ArrayList<>();
				ExceptionResponse exceptionResponse = e.getExceptionResponse();
				exceptionResponses.add(exceptionResponse);
				response.setErrors(exceptionResponses);
				return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

}
