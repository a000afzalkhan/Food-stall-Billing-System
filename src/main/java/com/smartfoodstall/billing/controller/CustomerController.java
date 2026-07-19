package com.smartfoodstall.billing.controller;

import com.smartfoodstall.billing.dto.OrderRequest;
import com.smartfoodstall.billing.model.Bill;
import com.smartfoodstall.billing.repository.FoodItemRepository;
import com.smartfoodstall.billing.service.BillingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    private final FoodItemRepository foodItemRepository;
    private final BillingService billingService;

    public CustomerController(FoodItemRepository foodItemRepository, BillingService billingService) {
        this.foodItemRepository = foodItemRepository;
        this.billingService = billingService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", foodItemRepository.findAll());
        model.addAttribute("orderRequest", new OrderRequest());
        return "customer";
    }

    @PostMapping("/bill/generate")
    public String generateBill(@ModelAttribute OrderRequest orderRequest, RedirectAttributes redirectAttributes) {
        try {
            Bill bill = billingService.generateBill(orderRequest);
            redirectAttributes.addFlashAttribute("bill", bill);
            return "redirect:/bill/success";
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/bill/success")
    public String billSuccess() {
        return "bill";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
