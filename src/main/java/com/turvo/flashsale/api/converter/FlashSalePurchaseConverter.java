package com.turvo.flashsale.api.converter;

import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.entity.FlashSalePurchase;

public class FlashSalePurchaseConverter {

	public static FlashSalePurchase convertDTOToEntity(FlashSalePurchaseDTO flashSalePurchaseDTO) {
		FlashSalePurchase flashSalePurchase = new FlashSalePurchase();

		flashSalePurchase.setId(flashSalePurchaseDTO.getId());
		flashSalePurchase.setInventoryId(flashSalePurchaseDTO.getInventoryId());
		flashSalePurchase.setUserId(flashSalePurchaseDTO.getUserId());

		return flashSalePurchase;
	}

	public static FlashSalePurchaseDTO convertEntityToDTO(FlashSalePurchase flashSalePurchase) {
		FlashSalePurchaseDTO flashSalePurchaseDTO = new FlashSalePurchaseDTO();

		flashSalePurchaseDTO.setId(flashSalePurchase.getId());
		flashSalePurchaseDTO.setInventoryId(flashSalePurchase.getInventoryId());
		flashSalePurchaseDTO.setUserId(flashSalePurchase.getUserId());

		return flashSalePurchaseDTO;
	}
}
