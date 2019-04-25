package com.turvo.flashsale.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.turvo.flashsale.api.common.ResponseFormat;
import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.service.FlashSalePurchaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@RequestScope
public class FlashSalePurchaseController {

	private static final Logger LOGGER = LogManager.getLogger(FlashSalePurchaseController.class);

	@Autowired
	private FlashSalePurchaseService flashSalePurchaseService;

	@ApiOperation(httpMethod = "POST", value = "Purchase flash sale products", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful Purchase", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/purchase")
	public ResponseEntity<ResponseFormat<FlashSalePurchaseDTO>> purchaseOrder(
			@RequestBody FlashSalePurchaseDTO flashSalePurchaseDTO) {

		LOGGER.info("Start com.turvo.flashsale.api.controller.FlashSalePurchaseController.purchaseOrder");

		ResponseFormat<FlashSalePurchaseDTO> response = null;

		flashSalePurchaseDTO = flashSalePurchaseService.purchaseOrder(flashSalePurchaseDTO);
		response = new ResponseFormat<>(flashSalePurchaseDTO, null);

		LOGGER.info("End com.turvo.flashsale.api.controller.FlashSalePurchaseController.purchaseOrder");

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
