package com.turvo.flashsale.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turvo.flashsale.api.entity.FlashSaleInventory;

@Repository
public interface FlashSaleInventoryRepository extends JpaRepository<FlashSaleInventory, Integer> {

}
