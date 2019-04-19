package com.turvo.flashsale.api.common;

import java.io.Serializable;
import java.util.List;

import com.turvo.flashsale.api.exception.ExceptionResponse;

public class ResponseFormat<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private T data;
	
	private List<ExceptionResponse> errors;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<ExceptionResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ExceptionResponse> errors) {
		this.errors = errors;
	}
	
}
