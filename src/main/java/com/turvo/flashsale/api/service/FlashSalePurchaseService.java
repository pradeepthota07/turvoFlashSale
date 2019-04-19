package com.turvo.flashsale.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.flashsale.api.converter.FlashSalePurchaseConverter;
import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.entity.FlashSalePurchase;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.exception.FlashSaleExceptionList;
import com.turvo.flashsale.api.repository.FlashSalePurchaseRepository;
import com.turvo.flashsale.api.validator.FlashSalePurchaseValidator;

@Service
public class FlashSalePurchaseService {

	@Autowired
	private FlashSalePurchaseValidator purchaseValidator;

	@Autowired
	private FlashSalePurchaseRepository purchaseRepository;

	@Autowired
	private FlashSaleInventoryService flashSaleInventoryService;

	public FlashSalePurchaseDTO purchaseOrder(FlashSalePurchaseDTO purchaseDTO)
			throws FlashSaleExceptionList, FlashSaleException {

		Date currentTime = new Date();
		List<ExceptionResponse> exceptionResponses = purchaseValidator.validatePurchase(purchaseDTO, currentTime);

		if (exceptionResponses != null && !exceptionResponses.isEmpty()) {
			throw new FlashSaleExceptionList(exceptionResponses);

		} else {
			FlashSaleInventoryDTO flashSaleInventoryDTO;
			try {
				flashSaleInventoryDTO = flashSaleInventoryService.getInventory(purchaseDTO.getInventoryId());

				if (flashSaleInventoryDTO != null && flashSaleInventoryDTO.getQuantity() >= 1) {
					FlashSalePurchase flashSalePurchase = FlashSalePurchaseConverter.convertDTOToEntity(purchaseDTO);

					purchaseDTO = FlashSalePurchaseConverter
							.convertEntityToDTO(purchaseRepository.save(flashSalePurchase));
					flashSaleInventoryDTO.setQuantity(flashSaleInventoryDTO.getQuantity() - 1);

					flashSaleInventoryService.saveInventory(flashSaleInventoryDTO);
				}
			} catch (FlashSaleException e) {
				throw new FlashSaleException(e.getExceptionResponse());
			}
		}

		return purchaseDTO;
	}
}
