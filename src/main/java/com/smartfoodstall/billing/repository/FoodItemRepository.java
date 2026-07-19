package com.smartfoodstall.billing.repository;

import com.smartfoodstall.billing.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
