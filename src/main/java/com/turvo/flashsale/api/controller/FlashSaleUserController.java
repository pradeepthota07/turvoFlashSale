package com.turvo.flashsale.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.turvo.flashsale.api.common.ResponseFormat;
import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.service.FlashSaleUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@RequestScope
public class FlashSaleUserController {

	private static final Logger LOGGER = LogManager.getLogger(FlashSaleUserController.class);
	
	@Autowired
	private FlashSaleUserService flashSaleUserService;

	@PostMapping("/users")
	@ApiOperation(httpMethod = "POST", value = "Register a user for flash sale", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Registration", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseFormat<FlashSaleUserDTO>> registerUser(@RequestBody FlashSaleUserDTO userDTO) {

		LOGGER.info("Start com.turvo.flashsale.api.controller.FlashSaleUserController.registerUser");
		
		ResponseFormat<FlashSaleUserDTO> response = null;

		FlashSaleUserDTO flashSaleUserDTO = null;

		if (userDTO.getUserId() != null) {
			flashSaleUserDTO = flashSaleUserService.registerUser(userDTO);
			response = new ResponseFormat<>(flashSaleUserDTO, null);
		}
		LOGGER.info("Start com.turvo.flashsale.api.controller.FlashSaleUserController.registerUser");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/users/{userId}")
	@ApiOperation(httpMethod = "GET", value = "Retrieve registered user details", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Retrieval", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseFormat<FlashSaleUserDTO>> retrieveUser(@PathVariable String userId) {
		
		LOGGER.info("Start com.turvo.flashsale.api.controller.FlashSaleUserController.retrieveUser");
		
		ResponseFormat<FlashSaleUserDTO> response = null;
		FlashSaleUserDTO flashSaleUserDTO = null;
		
		if (userId != null) {
			try {
				flashSaleUserDTO = flashSaleUserService.retrieveUser(userId);
				response = new ResponseFormat<>(flashSaleUserDTO, null);
			} catch (FlashSaleException e) {
				LOGGER.error(e.getExceptionResponses());
				response = new ResponseFormat<>(null, e.getExceptionResponses());
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}

		LOGGER.info("Start com.turvo.flashsale.api.controller.FlashSaleUserController.retrieveUser");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
