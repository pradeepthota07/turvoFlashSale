package com.turvo.flashsale.api.dto;

import java.io.Serializable;

public class FlashSalePurchaseDTO implements Serializable {

	private Integer id;

	private String userId;

	private Integer inventoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

}
