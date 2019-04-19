package com.turvo.flashsale.api.converter;

import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.entity.FlashSaleInventory;

public class FlashSaleInventoryConverter {

	public static FlashSaleInventory convertDTOToEntity(FlashSaleInventoryDTO flashSaleInventoryDTO) {
		FlashSaleInventory flashSaleInventory = new FlashSaleInventory();

		flashSaleInventory.setId(flashSaleInventoryDTO.getId());
		flashSaleInventory.setName(flashSaleInventoryDTO.getName());
		flashSaleInventory.setPrice(flashSaleInventoryDTO.getPrice());
		flashSaleInventory.setQuantity(flashSaleInventoryDTO.getQuantity());
		flashSaleInventory.setStartDate(flashSaleInventoryDTO.getStartDate());
		flashSaleInventory.setEndDate(flashSaleInventoryDTO.getEndDate());
		return flashSaleInventory;
	}

	public static FlashSaleInventoryDTO convertEntityToDTO(FlashSaleInventory flashSaleInventory) {
		FlashSaleInventoryDTO flashSaleInventoryDTO = new FlashSaleInventoryDTO();

		flashSaleInventoryDTO.setId(flashSaleInventory.getId());
		flashSaleInventoryDTO.setName(flashSaleInventory.getName());
		flashSaleInventoryDTO.setPrice(flashSaleInventory.getPrice());
		flashSaleInventoryDTO.setQuantity(flashSaleInventory.getQuantity());
		flashSaleInventoryDTO.setStartDate(flashSaleInventory.getStartDate());
		flashSaleInventoryDTO.setEndDate(flashSaleInventory.getEndDate());
		return flashSaleInventoryDTO;
	}
}
