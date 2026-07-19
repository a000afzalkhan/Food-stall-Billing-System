package com.smartfoodstall.billing.service;

import com.smartfoodstall.billing.dto.OrderRequest;
import com.smartfoodstall.billing.model.Bill;
import com.smartfoodstall.billing.model.BillItem;
import com.smartfoodstall.billing.model.FoodItem;
import com.smartfoodstall.billing.repository.BillRepository;
import com.smartfoodstall.billing.repository.FoodItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class BillingService {

    private static final BigDecimal GST_RATE = new BigDecimal("0.05");

    private final FoodItemRepository foodItemRepository;
    private final BillRepository billRepository;

    public BillingService(FoodItemRepository foodItemRepository, BillRepository billRepository) {
        this.foodItemRepository = foodItemRepository;
        this.billRepository = billRepository;
    }

    @Transactional
    public Bill generateBill(OrderRequest orderRequest) {
        Map<Long, Integer> quantities = orderRequest.getQuantities();
        List<FoodItem> orderedItems = foodItemRepository.findAllById(quantities.keySet());

        BigDecimal subtotal = BigDecimal.ZERO;
        Bill bill = new Bill(LocalDateTime.now(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

        for (FoodItem item : orderedItems) {
            int quantity = Math.max(0, quantities.getOrDefault(item.getId(), 0));
            if (quantity == 0) {
                continue;
            }
            if (quantity > item.getStockQuantity()) {
                throw new IllegalArgumentException(item.getName() + " has only " + item.getStockQuantity() + " item(s) in stock.");
            }

            BigDecimal lineTotal = item.getPrice().multiply(BigDecimal.valueOf(quantity));
            subtotal = subtotal.add(lineTotal);
            item.setStockQuantity(item.getStockQuantity() - quantity);
            foodItemRepository.save(item);
            bill.addItem(new BillItem(item.getName(), quantity, item.getPrice(), lineTotal));
        }

        if (bill.getItems().isEmpty()) {
            throw new IllegalArgumentException("Please select at least one food item.");
        }

        BigDecimal gst = orderRequest.isGstEnabled()
                ? subtotal.multiply(GST_RATE).setScale(2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        BigDecimal grandTotal = subtotal.add(gst).setScale(2, RoundingMode.HALF_UP);

        Bill completedBill = new Bill(LocalDateTime.now(), subtotal.setScale(2, RoundingMode.HALF_UP), gst, grandTotal);
        bill.getItems().forEach(completedBill::addItem);
        return billRepository.save(completedBill);
    }

    public BigDecimal getTodaySales() {
        LocalDate today = LocalDate.now();
        return billRepository.totalSalesBetween(today.atStartOfDay(), today.plusDays(1).atStartOfDay());
    }

    public long getTotalBills() {
        return billRepository.count();
    }

    public List<Bill> getRecentBills() {
        return billRepository.findAll().stream()
                .sorted((first, second) -> second.getCreatedAt().compareTo(first.getCreatedAt()))
                .limit(10)
                .toList();
    }
}
