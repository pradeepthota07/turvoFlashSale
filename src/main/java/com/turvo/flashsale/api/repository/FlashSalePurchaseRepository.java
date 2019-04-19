package com.turvo.flashsale.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turvo.flashsale.api.entity.FlashSalePurchase;

@Repository
public interface FlashSalePurchaseRepository extends JpaRepository<FlashSalePurchase, Integer> {

}
