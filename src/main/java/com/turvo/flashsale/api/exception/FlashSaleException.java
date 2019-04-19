package com.turvo.flashsale.api.exception;

public class FlashSaleException extends Exception {

	private static final long serialVersionUID = 1L;

	private ExceptionResponse exceptionResponse;

	public FlashSaleException(String exception) {
		super(exception);
	}

	public FlashSaleException(ExceptionResponse exceptionResponse) {
		this.exceptionResponse = exceptionResponse;
	}

	public ExceptionResponse getExceptionResponse() {
		return exceptionResponse;
	}
}
