package com.turvo.flashsale.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.flashsale.api.converter.FlashSaleInventoryConverter;
import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.entity.FlashSaleInventory;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.repository.FlashSaleInventoryRepository;

@Service
public class FlashSaleInventoryService {

	@Autowired
	private FlashSaleInventoryRepository inventoryRepo;

	public FlashSaleInventoryDTO saveInventory(FlashSaleInventoryDTO fSaleInventoryDTO) {

		FlashSaleInventoryDTO inventoryDTO = null;

		// Validations of FlashSale Inventory DTO if any

		if (fSaleInventoryDTO != null) {

			FlashSaleInventory flashSaleInventory = FlashSaleInventoryConverter.convertDTOToEntity(fSaleInventoryDTO);

			inventoryDTO = FlashSaleInventoryConverter.convertEntityToDTO(inventoryRepo.save(flashSaleInventory));
		}

		return inventoryDTO;
	}

	public FlashSaleInventoryDTO getInventory(Integer inventoryId) throws FlashSaleException {

		FlashSaleInventoryDTO inventoryDTO = null;

		if (inventoryId > 0) {
			Optional<FlashSaleInventory> flashSaleInventory = inventoryRepo.findById(inventoryId);

			if (flashSaleInventory.isPresent()) {
				inventoryDTO = FlashSaleInventoryConverter.convertEntityToDTO(flashSaleInventory.get());
			} else {
				ExceptionResponse exceptionResponse = new ExceptionResponse();
				exceptionResponse.setCode("FSI-001");
				exceptionResponse.setMessage("Inventory not for sale");
				throw new FlashSaleException(exceptionResponse);
			}
		}
		return inventoryDTO;
	}
}
