package com.smartfoodstall.billing.controller;

import com.smartfoodstall.billing.model.FoodItem;
import com.smartfoodstall.billing.repository.FoodItemRepository;
import com.smartfoodstall.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
public class AdminController {

    private final FoodItemRepository foodItemRepository;
    private final BillingService billingService;

    public AdminController(FoodItemRepository foodItemRepository, BillingService billingService) {
        this.foodItemRepository = foodItemRepository;
        this.billingService = billingService;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("items", foodItemRepository.findAll());
        model.addAttribute("newItem", new FoodItem("", BigDecimal.ONE, 0));
        model.addAttribute("todaySales", billingService.getTodaySales());
        model.addAttribute("totalBills", billingService.getTotalBills());
        model.addAttribute("recentBills", billingService.getRecentBills());
        return "admin";
    }

    @PostMapping("/admin/items")
    public String addItem(@Valid @ModelAttribute("newItem") FoodItem foodItem, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            foodItemRepository.save(foodItem);
        }
        return "redirect:/admin";
    }

    @PostMapping("/admin/items/{id}/update")
    public String updateItem(@PathVariable Long id, FoodItem formItem, RedirectAttributes redirectAttributes) {
        return foodItemRepository.findById(id)
                .map(item -> {
                    item.setName(formItem.getName());
                    item.setPrice(formItem.getPrice().max(BigDecimal.ONE));
                    item.setStockQuantity(Math.max(0, formItem.getStockQuantity()));
                    foodItemRepository.save(item);
                    return "redirect:/admin";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("error", "Item not found.");
                    return "redirect:/admin";
                });
    }

    @PostMapping("/admin/items/{id}/price-up")
    public String priceUp(@PathVariable Long id) {
        foodItemRepository.findById(id).ifPresent(item -> {
            item.setPrice(item.getPrice().add(new BigDecimal("10")));
            foodItemRepository.save(item);
        });
        return "redirect:/admin";
    }

    @PostMapping("/admin/items/{id}/price-down")
    public String priceDown(@PathVariable Long id) {
        foodItemRepository.findById(id).ifPresent(item -> {
            item.setPrice(item.getPrice().subtract(new BigDecimal("10")).max(BigDecimal.ONE));
            foodItemRepository.save(item);
        });
        return "redirect:/admin";
    }

    @PostMapping("/admin/items/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        foodItemRepository.deleteById(id);
        return "redirect:/admin";
    }
}
