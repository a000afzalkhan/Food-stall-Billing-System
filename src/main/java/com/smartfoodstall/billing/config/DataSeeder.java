package com.smartfoodstall.billing.config;

import com.smartfoodstall.billing.model.FoodItem;
import com.smartfoodstall.billing.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedFoodItems(FoodItemRepository foodItemRepository) {
        return args -> {
            if (foodItemRepository.count() > 0) {
                return;
            }

            foodItemRepository.saveAll(List.of(
                    new FoodItem("Burger", new BigDecimal("80"), 40),
                    new FoodItem("Pizza", new BigDecimal("250"), 20),
                    new FoodItem("Cold Drink", new BigDecimal("40"), 60),
                    new FoodItem("French Fries", new BigDecimal("90"), 35),
                    new FoodItem("Veg Sandwich", new BigDecimal("70"), 45),
                    new FoodItem("Paneer Roll", new BigDecimal("120"), 30),
                    new FoodItem("Momos", new BigDecimal("100"), 25),
                    new FoodItem("Pasta", new BigDecimal("160"), 18),
                    new FoodItem("Noodles", new BigDecimal("140"), 24),
                    new FoodItem("Samosa", new BigDecimal("20"), 80),
                    new FoodItem("Tea", new BigDecimal("15"), 100),
                    new FoodItem("Coffee", new BigDecimal("30"), 70),
                    new FoodItem("Ice Cream", new BigDecimal("60"), 32)
            ));
        };
    }
}
