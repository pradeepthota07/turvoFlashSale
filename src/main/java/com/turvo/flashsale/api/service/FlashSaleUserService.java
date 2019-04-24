package com.turvo.flashsale.api.service;

import com.turvo.flashsale.api.dto.FlashSaleUserDTO;

public interface FlashSaleUserService {

	FlashSaleUserDTO registerUser(FlashSaleUserDTO flashSaleUserDTO);

	FlashSaleUserDTO retrieveUser(String userId);
}
