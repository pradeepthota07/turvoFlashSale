package com.turvo.flashsale.api.common;

import java.io.Serializable;
import java.util.List;

import com.turvo.flashsale.api.exception.ExceptionResponse;

public class ResponseFormat<T extends Serializable> implements Serializable {

	private final T data;

	private final List<ExceptionResponse> errors;

	public ResponseFormat(T data, List<ExceptionResponse> errors) {
		this.data = data;
		this.errors = errors;
	}

	public T getData() {
		return data;
	}

	public List<ExceptionResponse> getErrors() {
		return errors;
	}

}
