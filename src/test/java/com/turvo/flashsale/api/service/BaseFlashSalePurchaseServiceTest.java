package com.turvo.flashsale.api.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.dto.FlashSalePurchaseDTO;
import com.turvo.flashsale.api.entity.FlashSalePurchase;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.repository.FlashSalePurchaseRepository;
import com.turvo.flashsale.api.validator.FlashSalePurchaseValidator;

@RunWith(MockitoJUnitRunner.class)
public class BaseFlashSalePurchaseServiceTest {

	@InjectMocks
	BaseFlashSalePurchaseService purchaseService;

	@Mock
	FlashSalePurchaseValidator purchaseValidator;

	@Mock
	FlashSalePurchaseRepository purchaseRepository;

	@Mock
	BaseFlashSaleInventoryService flashSaleInventoryService;

	@Test
	public void purchaseOrder() {

		List<ExceptionResponse> exceptionResponses = new ArrayList<>();

		Mockito.when(purchaseValidator.validatePurchase(Mockito.any(FlashSalePurchaseDTO.class)))
				.thenReturn(exceptionResponses);

		FlashSaleInventoryDTO inventoryDTO = new FlashSaleInventoryDTO();
		inventoryDTO.setId(1);
		inventoryDTO.setName("Watch");
		inventoryDTO.setPrice(23.50);
		inventoryDTO.setStartDate(new Date());
		inventoryDTO.setEndDate(new Date());
		inventoryDTO.setQuantity(2);

		FlashSalePurchaseDTO purchaseDTO = new FlashSalePurchaseDTO();
		purchaseDTO.setId(1);
		purchaseDTO.setInventoryId(1);
		purchaseDTO.setUserId("ab23456");

		FlashSalePurchase purchase = new FlashSalePurchase();
		purchase.setId(1);
		purchase.setInventoryId(1);
		purchase.setUserId("ab23456");

		Mockito.when(flashSaleInventoryService.getInventory(Mockito.any(Integer.class))).thenReturn(inventoryDTO);
		Mockito.when(flashSaleInventoryService.saveInventory(Mockito.any(FlashSaleInventoryDTO.class)))
				.thenReturn(inventoryDTO);
		Mockito.when(purchaseRepository.save(Mockito.any(FlashSalePurchase.class))).thenReturn(purchase);
		
		FlashSalePurchaseDTO flashSalePurchaseDTO = purchaseService.purchaseOrder(purchaseDTO);
		
		assertEquals(1, flashSalePurchaseDTO.getId().intValue());
		assertEquals(1, flashSalePurchaseDTO.getInventoryId().intValue());
		assertEquals("ab23456", flashSalePurchaseDTO.getUserId());
	}
}
