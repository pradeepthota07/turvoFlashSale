package com.turvo.flashsale.api.exception;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private String code;

	private String message;

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
