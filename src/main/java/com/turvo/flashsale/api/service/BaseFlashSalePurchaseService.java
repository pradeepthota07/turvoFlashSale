package com.turvo.flashsale.api.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.flashsale.api.converter.FlashSalePurchaseConverter;
import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.entity.FlashSalePurchase;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.repository.FlashSalePurchaseRepository;
import com.turvo.flashsale.api.validator.FlashSalePurchaseValidator;

@Service
public class BaseFlashSalePurchaseService implements FlashSalePurchaseService {

	private static final Logger LOGGER = LogManager.getLogger(BaseFlashSalePurchaseService.class);

	private FlashSalePurchaseValidator purchaseValidator;

	private FlashSalePurchaseRepository purchaseRepository;

	private FlashSaleInventoryService flashSaleInventoryService;

	@Autowired
	public BaseFlashSalePurchaseService(FlashSalePurchaseRepository purchaseRepository,
			BaseFlashSaleInventoryService flashSaleInventoryService, FlashSalePurchaseValidator purchaseValidator) {
		this.flashSaleInventoryService = flashSaleInventoryService;
		this.purchaseRepository = purchaseRepository;
		this.purchaseValidator = purchaseValidator;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FlashSalePurchaseDTO purchaseOrder(FlashSalePurchaseDTO purchaseDTO) {
		LOGGER.info("Start com.turvo.flashsale.api.service.FlashSalePurchaseBaseService.purchaseOrder");

		List<ExceptionResponse> exceptionResponses = purchaseValidator.validatePurchase(purchaseDTO);

		if (exceptionResponses != null && !exceptionResponses.isEmpty()) {
			throw new FlashSaleException(exceptionResponses);
		} else {
			FlashSaleInventoryDTO flashSaleInventoryDTO;
			flashSaleInventoryDTO = flashSaleInventoryService.getInventory(purchaseDTO.getInventoryId());
			if (flashSaleInventoryDTO != null && flashSaleInventoryDTO.getQuantity() >= 1) {
				FlashSalePurchase flashSalePurchase = FlashSalePurchaseConverter.convertDTOToEntity(purchaseDTO);

				flashSaleInventoryDTO.setQuantity(flashSaleInventoryDTO.getQuantity() - 1);

				flashSaleInventoryService.saveInventory(flashSaleInventoryDTO);
				purchaseDTO = FlashSalePurchaseConverter.convertEntityToDTO(purchaseRepository.save(flashSalePurchase));
			}
		}

		LOGGER.info("End com.turvo.flashsale.api.service.FlashSalePurchaseBaseService.purchaseOrder");
		return purchaseDTO;
	}
}
