package com.turvo.flashsale.api.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.turvo.flashsale.api.dto.FlashSaleInventoryDTO;
import com.turvo.flashsale.api.entity.FlashSaleInventory;
import com.turvo.flashsale.api.repository.FlashSaleInventoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class BaseFlashSaleInventoryServiceTest {

	@InjectMocks
	BaseFlashSaleInventoryService inventoryService;

	@Mock
	FlashSaleInventoryRepository inventoryRepo;

	@Test
	public void saveInventory() {
		FlashSaleInventoryDTO inventoryDTO = new FlashSaleInventoryDTO();
		inventoryDTO.setId(1);
		inventoryDTO.setName("Watch");
		inventoryDTO.setPrice(23.50);
		inventoryDTO.setStartDate(new Date());
		inventoryDTO.setEndDate(new Date());

		FlashSaleInventory inventory = new FlashSaleInventory();
		inventory.setId(1);
		inventory.setName("Watch");
		inventory.setPrice(23.50);
		inventory.setStartDate(new Date());
		inventory.setEndDate(new Date());

		Mockito.when(inventoryRepo.save(Mockito.any(FlashSaleInventory.class))).thenReturn(inventory);

		FlashSaleInventoryDTO flashSaleInventoryDTO = inventoryService.saveInventory(inventoryDTO);

		assertEquals("Watch", flashSaleInventoryDTO.getName());
		assertEquals(1, flashSaleInventoryDTO.getId().intValue());
	}
	
	@Test
	public void getInventory() {
		FlashSaleInventoryDTO inventoryDTO = new FlashSaleInventoryDTO();
		inventoryDTO.setId(1);
		inventoryDTO.setName("Watch");
		inventoryDTO.setPrice(23.50);
		inventoryDTO.setStartDate(new Date());
		inventoryDTO.setEndDate(new Date());

		FlashSaleInventory inventory = new FlashSaleInventory();
		inventory.setId(1);
		inventory.setName("Watch");
		inventory.setPrice(23.50);
		inventory.setStartDate(new Date());
		inventory.setEndDate(new Date());

		Mockito.when(inventoryRepo.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(inventory));

		FlashSaleInventoryDTO flashSaleInventoryDTO = inventoryService.getInventory(1);

		assertEquals("Watch", flashSaleInventoryDTO.getName());
		assertEquals(1, flashSaleInventoryDTO.getId().intValue());
	}

}
