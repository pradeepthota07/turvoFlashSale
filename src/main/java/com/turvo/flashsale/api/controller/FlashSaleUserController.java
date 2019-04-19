package com.turvo.flashsale.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.service.FlashSaleUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@RequestScope
public class FlashSaleUserController {

	@Autowired
	private FlashSaleUserService flashSaleUserService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/users")
	@ApiOperation(httpMethod = "POST", value = "Register a user for flash sale", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful Registration", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity registerUser(@RequestBody FlashSaleUserDTO userDTO) {

		ResponseFormat<FlashSaleUserDTO> response = new ResponseFormat<>();
		
		FlashSaleUserDTO flashSaleUserDTO = null;

		if (userDTO.getUserId() != null) {
			flashSaleUserDTO = flashSaleUserService.registerUser(userDTO);
			response.setData(flashSaleUserDTO);
		}
		return new ResponseEntity(response, HttpStatus.CREATED);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/users/{userId}")
	@ApiOperation(httpMethod = "GET", value = "Retrieve registered user details", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful Retrieval", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity getUser(@PathVariable String userId) {
		ResponseFormat<FlashSaleUserDTO> response = new ResponseFormat<>();
		FlashSaleUserDTO flashSaleUserDTO = null;
		
		if(userId!=null || userId!="") {
			try {
				flashSaleUserDTO = flashSaleUserService.getUser(userId);
				response.setData(flashSaleUserDTO);
			} catch (FlashSaleException e) {
				List<ExceptionResponse> exceptionResponses = new ArrayList<>();
				ExceptionResponse exceptionResponse = e.getExceptionResponse();
				exceptionResponses.add(exceptionResponse);
				response.setErrors(exceptionResponses);
				return new ResponseEntity(response,HttpStatus.NOT_FOUND);
			}
		}
		
		return ResponseEntity.ok(response);
	}
}
