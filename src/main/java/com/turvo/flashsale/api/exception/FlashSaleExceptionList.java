package com.turvo.flashsale.api.exception;

import java.util.List;

public class FlashSaleExceptionList extends Exception {

	private static final long serialVersionUID = 1L;

	private List<ExceptionResponse> exceptionResponses;

	public FlashSaleExceptionList(String exception) {
		super(exception);
	}

	public FlashSaleExceptionList(List<ExceptionResponse> exceptionResponses) {
		this.exceptionResponses = exceptionResponses;
	}

	public List<ExceptionResponse> getExceptionResponses() {
		return exceptionResponses;
	}

}