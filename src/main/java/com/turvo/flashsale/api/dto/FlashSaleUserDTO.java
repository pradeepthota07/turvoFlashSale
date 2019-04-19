package com.turvo.flashsale.api.dto;

import java.io.Serializable;

public class FlashSaleUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private boolean isRegistered;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
}
