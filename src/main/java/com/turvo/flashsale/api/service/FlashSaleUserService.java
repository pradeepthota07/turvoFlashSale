package com.turvo.flashsale.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.flashsale.api.converter.FlashSaleUserConverter;
import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.entity.FlashSaleUser;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.FlashSaleException;
import com.turvo.flashsale.api.repository.FlashSaleUserRepository;

@Service
public class FlashSaleUserService {

	@Autowired
	private FlashSaleUserRepository userRepo;

	public FlashSaleUserDTO registerUser(FlashSaleUserDTO flashSaleUserDTO) {
		// Validate if the user is existing user of the ecommerce system
		
		FlashSaleUserDTO userDTO = null;

		if (flashSaleUserDTO.getUserId() != null) {
			FlashSaleUser flashSaleUser = FlashSaleUserConverter.convertDTOToEntity(flashSaleUserDTO);
			flashSaleUser.setRegistered(true);

			userDTO = FlashSaleUserConverter.convertEntityToDTO(userRepo.save(flashSaleUser));
		}

		return userDTO;
	}

	public FlashSaleUserDTO getUser(String userId) throws FlashSaleException{
		// Validate if the user is existing user of the e-commerce system
		
		FlashSaleUserDTO userDTO = null;
		
		if (userId != null) {
			Optional<FlashSaleUser> flashSaleUser = userRepo.findById(userId);
			
			if(flashSaleUser.isPresent()) {
				userDTO = FlashSaleUserConverter.convertEntityToDTO(flashSaleUser.get());
			} else {
				ExceptionResponse exceptionResponse = new ExceptionResponse();
				exceptionResponse.setCode("FSU-001");
				exceptionResponse.setMessage("User not Found");
				throw new FlashSaleException(exceptionResponse);
			}
		}

		return userDTO;
		
	}

}
