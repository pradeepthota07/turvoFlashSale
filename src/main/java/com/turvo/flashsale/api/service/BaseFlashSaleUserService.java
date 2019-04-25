package com.turvo.flashsale.api.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.turvo.flashsale.api.converter.FlashSaleUserConverter;
import com.turvo.flashsale.api.dto.FlashSaleUserDTO;
import com.turvo.flashsale.api.entity.FlashSaleUser;
import com.turvo.flashsale.api.exception.ExceptionResponse;
import com.turvo.flashsale.api.exception.ResourceNotFoundException;
import com.turvo.flashsale.api.repository.FlashSaleUserRepository;

@Service
public class BaseFlashSaleUserService implements FlashSaleUserService {

	private static final Logger LOGGER = LogManager.getLogger(BaseFlashSaleUserService.class);

	private FlashSaleUserRepository userRepo;

	@Autowired
	public BaseFlashSaleUserService(FlashSaleUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FlashSaleUserDTO registerUser(FlashSaleUserDTO flashSaleUserDTO) {
		// Validate if the user is existing user of the ecommerce system

		LOGGER.info("Start com.turvo.flashsale.api.service.FlashSaleUserBaseService.registerUser");

		FlashSaleUserDTO userDTO = null;

		if (flashSaleUserDTO.getUserId() != null) {
			FlashSaleUser flashSaleUser = FlashSaleUserConverter.convertDTOToEntity(flashSaleUserDTO);
			flashSaleUser.setRegistered(true);

			userDTO = FlashSaleUserConverter.convertEntityToDTO(userRepo.save(flashSaleUser));
		}

		LOGGER.info("End com.turvo.flashsale.api.service.FlashSaleUserBaseService.registerUser");

		return userDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FlashSaleUserDTO retrieveUser(String userId) {
		// Validate if the user is existing user of the e-commerce system

		LOGGER.info("Start com.turvo.flashsale.api.service.FlashSaleUserBaseService.retrieveUser");

		FlashSaleUserDTO userDTO = null;

		if (userId != null) {
			Optional<FlashSaleUser> flashSaleUser = userRepo.findById(userId);

			if (flashSaleUser.isPresent()) {
				userDTO = FlashSaleUserConverter.convertEntityToDTO(flashSaleUser.get());
			} else {
				ExceptionResponse ex = new ExceptionResponse("FSI-001", "User not registered");
				throw new ResourceNotFoundException(ex);
			}
		}

		LOGGER.info("End com.turvo.flashsale.api.service.FlashSaleUserBaseService.retrieveUser");

		return userDTO;

	}

}
