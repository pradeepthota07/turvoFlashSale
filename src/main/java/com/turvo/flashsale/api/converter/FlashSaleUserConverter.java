package com.turvo.flashsale.api.converter;

import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.entity.FlashSaleUser;

public class FlashSaleUserConverter {

	public static FlashSaleUser convertDTOToEntity(FlashSaleUserDTO flashSaleUserDTO) {
		FlashSaleUser flashSaleUser = new FlashSaleUser();

		flashSaleUser.setRegistered(flashSaleUserDTO.isRegistered());
		flashSaleUser.setUserId(flashSaleUserDTO.getUserId());

		return flashSaleUser;
	}

	public static FlashSaleUserDTO convertEntityToDTO(FlashSaleUser flashSaleUser) {
		FlashSaleUserDTO flashSaleUserDTO = new FlashSaleUserDTO();

		flashSaleUserDTO.setRegistered(flashSaleUser.isRegistered());
		flashSaleUserDTO.setUserId(flashSaleUser.getUserId());

		return flashSaleUserDTO;
	}
}
