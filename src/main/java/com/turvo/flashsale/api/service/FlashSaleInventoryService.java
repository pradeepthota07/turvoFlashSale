package com.turvo.flashsale.api.service;

import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;

public interface FlashSaleInventoryService {

	FlashSaleInventoryDTO saveInventory(FlashSaleInventoryDTO fSaleInventoryDTO);

	FlashSaleInventoryDTO getInventory(Integer inventoryId);
}
