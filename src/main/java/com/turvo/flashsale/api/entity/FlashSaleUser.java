package com.turvo.flashsale.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FlashSaleUser {

	@Id
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
