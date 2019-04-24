package com.turvo.flashsale.api.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.flashsale.api.converter.FlashSaleInventoryConverter;
import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.entity.FlashSaleInventory;
import com.turvo.flashsale.api.repository.FlashSaleInventoryRepository;

@Service
public class BaseFlashSaleInventoryService implements FlashSaleInventoryService {

	private static final Logger LOGGER = LogManager.getLogger(BaseFlashSaleInventoryService.class);

	private FlashSaleInventoryRepository inventoryRepo;

	@Autowired
	public BaseFlashSaleInventoryService(FlashSaleInventoryRepository inventoryRepo) {
		this.inventoryRepo = inventoryRepo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FlashSaleInventoryDTO saveInventory(FlashSaleInventoryDTO fSaleInventoryDTO) {

		LOGGER.info("Start com.turvo.flashsale.api.service.FlashSaleInventoryBaseService.saveInventory");

		FlashSaleInventoryDTO inventoryDTO = null;

		// Validations of FlashSale Inventory DTO if any
		if (fSaleInventoryDTO != null) {
			FlashSaleInventory flashSaleInventory = FlashSaleInventoryConverter.convertDTOToEntity(fSaleInventoryDTO);
			inventoryDTO = FlashSaleInventoryConverter.convertEntityToDTO(inventoryRepo.save(flashSaleInventory));
		}

		LOGGER.info("End com.turvo.flashsale.api.service.FlashSaleInventoryBaseService.saveInventory");

		return inventoryDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FlashSaleInventoryDTO getInventory(Integer inventoryId) {

		LOGGER.info("Start com.turvo.flashsale.api.service.FlashSaleInventoryBaseService.getInventory");
		
		FlashSaleInventoryDTO inventoryDTO = null;

		if (inventoryId > 0) {
			Optional<FlashSaleInventory> flashSaleInventory = inventoryRepo.findById(inventoryId);

			if (flashSaleInventory.isPresent()) {
				inventoryDTO = FlashSaleInventoryConverter.convertEntityToDTO(flashSaleInventory.get());
			}

		}
		
		LOGGER.info("End com.turvo.flashsale.api.service.FlashSaleInventoryBaseService.getInventory");
		
		return inventoryDTO;
	}
}
