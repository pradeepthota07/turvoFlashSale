package com.turvo.flashsale.api.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.entity.FlashSaleUser;
import com.turvo.flashsale.api.repository.FlashSaleUserRepository;

@RunWith(MockitoJUnitRunner.class)
public class BaseFlashSaleUserServiceTest {

	@InjectMocks
	BaseFlashSaleUserService userService;

	@Mock
	FlashSaleUserRepository userRepo;

	@Test
	public void registerUser() {

		FlashSaleUserDTO userDTO = new FlashSaleUserDTO();
		userDTO.setUserId("ab23547");
		userDTO.setRegistered(true);
		
		FlashSaleUser flashSaleUser = new FlashSaleUser();
		flashSaleUser.setUserId("ab23547");
		flashSaleUser.setRegistered(true);
		
		Mockito.when(userRepo.save(Mockito.any(FlashSaleUser.class))).thenReturn(flashSaleUser);
		
		FlashSaleUserDTO flashSaleUserDTO = userService.registerUser(userDTO);

		assertEquals("ab23547", flashSaleUserDTO.getUserId());
		assertEquals(true, flashSaleUserDTO.isRegistered());

	}
	
	@Test
	public void retrieveUser() {
		
		FlashSaleUserDTO userDTO = new FlashSaleUserDTO();
		userDTO.setUserId("ab23547");
		userDTO.setRegistered(true);
		
		FlashSaleUser flashSaleUser = new FlashSaleUser();
		flashSaleUser.setUserId("ab23547");
		flashSaleUser.setRegistered(true);
		
		Mockito.when(userRepo.findById(Mockito.any(String.class))).thenReturn(Optional.of(flashSaleUser));
		
		FlashSaleUserDTO flashSaleUserDTO = userService.retrieveUser("ab23547");
		
		assertEquals("ab23547", flashSaleUserDTO.getUserId());
		assertEquals(true, flashSaleUserDTO.isRegistered());
	}
}
