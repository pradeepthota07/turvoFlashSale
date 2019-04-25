package com.turvo.flashsale.api.exception;

import java.util.ArrayList;
import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

	private List<ExceptionResponse> exceptionResponses;

	public ResourceNotFoundException(String message, List<ExceptionResponse> exceptionResponses) {
		super(message);
		this.exceptionResponses = exceptionResponses;
	}

	public ResourceNotFoundException(List<ExceptionResponse> exceptionResponses) {
		this.exceptionResponses = exceptionResponses;
	}

	public ResourceNotFoundException(ExceptionResponse exceptionResponse) {
		this.exceptionResponses = new ArrayList<>();
		this.exceptionResponses.add(exceptionResponse);
	}

	public List<ExceptionResponse> getExceptionResponses() {
		return exceptionResponses;
	}

	public void addExceptionResponse(ExceptionResponse exceptionResponse) {
		if (this.exceptionResponses == null || this.exceptionResponses.isEmpty()) {
			this.exceptionResponses = new ArrayList<>();
			this.exceptionResponses.add(exceptionResponse);
		} else {
			this.exceptionResponses.add(exceptionResponse);
		}
	}
}
