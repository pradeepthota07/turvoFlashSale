package com.turvo.flashsale.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.turvo.flashsale.api.common.ResponseFormat;

@ControllerAdvice
public class ExceptionController {

	private static final Logger LOGGER = LogManager.getLogger(ExceptionController.class);

	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ FlashSaleException.class })
	public ResponseEntity<ResponseFormat> handleFlashSaleExceptions(FlashSaleException e) {
		LOGGER.log(Level.ERROR, e.getExceptionResponses());

		ResponseFormat response = new ResponseFormat<>(null, e.getExceptionResponses());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<ResponseFormat> handleResourceNotFound(ResourceNotFoundException e) {
		LOGGER.log(Level.ERROR, e.getExceptionResponses());

		ResponseFormat response = new ResponseFormat<>(null, e.getExceptionResponses());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<ResponseFormat> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		LOGGER.log(Level.ERROR, e.getMessage());

		ExceptionResponse exResponse = new ExceptionResponse("DI-001","Invalid request/ Item not available");
		List<ExceptionResponse> exceptionResponses = new ArrayList<>();
		exceptionResponses.add(exResponse);
		ResponseFormat response = new ResponseFormat<>(null, exceptionResponses);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
