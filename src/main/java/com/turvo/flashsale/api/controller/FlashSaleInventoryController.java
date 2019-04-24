package com.turvo.flashsale.api.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.turvo.flashsale.api.common.ResponseFormat;
import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.service.FlashSaleInventoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@RequestScope
public class FlashSaleInventoryController {
	
	private static final Logger LOGGER = LogManager.getLogger(FlashSaleInventoryController.class);

	@Autowired
	private FlashSaleInventoryService flashSaleInventoryService;

	@PostMapping("/inventories")
	@ApiOperation(httpMethod = "POST", value = "Adds goods to the inventory", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful creation", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseFormat<FlashSaleInventoryDTO>> addInventory(
			@RequestBody FlashSaleInventoryDTO inventoryDTO) {

		LOGGER.info("Start com.turvo.flashsale.api.service.FlashSaleInventoryBaseService.addInventory");
		
		ResponseFormat<FlashSaleInventoryDTO> response = null;
		try {
			if (inventoryDTO != null) {
				inventoryDTO = flashSaleInventoryService.saveInventory(inventoryDTO);
				response = new ResponseFormat<>(inventoryDTO, null);
			}
		} catch (FlashSaleException e) {
			LOGGER.log(Level.ERROR, e.getExceptionResponses());
			response = new ResponseFormat<>(null, e.getExceptionResponses());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("End com.turvo.flashsale.api.service.FlashSaleInventoryBaseService.addInventory");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/inventories/{id}")
	@ApiOperation(httpMethod = "PUT", value = "Updates goods of the inventory", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Update Accepted", response = ResponseFormat.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResponseFormat<FlashSaleInventoryDTO>> updateInventory(@PathVariable String id,
			@RequestBody FlashSaleInventoryDTO inventoryDTO) {

		LOGGER.info("Start com.turvo.flashsale.api.controller.FlashSaleInventoryController.updateInventory");
		
		ResponseFormat<FlashSaleInventoryDTO> response = null;
		try {
			if (inventoryDTO != null && id != null) {
				Integer inventoryId = Integer.parseInt(id);
				inventoryDTO.setId(inventoryId);
				inventoryDTO = flashSaleInventoryService.saveInventory(inventoryDTO);
				response = new ResponseFormat<>(inventoryDTO, null);
			}
		} catch (FlashSaleException e) {
			LOGGER.log(Level.ERROR, e.getExceptionResponses());
			response = new ResponseFormat<>(null, e.getExceptionResponses());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("End com.turvo.flashsale.api.controller.FlashSaleInventoryController.updateInventory");
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

}
