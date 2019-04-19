package com.turvo.flashsale.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turvo.flashsale.api.common.ResponseFormat;
import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.service.FlashSaleInventoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
public class FlashSaleInventoryController {

	@Autowired
	private FlashSaleInventoryService flashSaleInventoryService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/inventories")
	@ApiOperation(httpMethod = "POST", value = "Adds goods to the inventory", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successful creation", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity addInventory(@RequestBody FlashSaleInventoryDTO inventoryDTO) {

		ResponseFormat<FlashSaleInventoryDTO> response = new ResponseFormat<>();
		if (inventoryDTO != null) {
			inventoryDTO = flashSaleInventoryService.saveInventory(inventoryDTO);
			response.setData(inventoryDTO);
		}

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/inventories/{id}")
	@ApiOperation(httpMethod = "PUT", value = "Updates goods of the inventory", produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Update Accepted", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity updateInventory(@PathVariable String id, @RequestBody FlashSaleInventoryDTO inventoryDTO) {

		ResponseFormat<FlashSaleInventoryDTO> response = new ResponseFormat<>();
		if (inventoryDTO != null && id != null) {
			Integer inventoryId = Integer.parseInt(id);
			inventoryDTO.setId(inventoryId);
			inventoryDTO = flashSaleInventoryService.saveInventory(inventoryDTO);
			response.setData(inventoryDTO);
		}

		return new ResponseEntity(response, HttpStatus.ACCEPTED);
	}

}
