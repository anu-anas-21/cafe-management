package com.downtowncafe.cafemanagement.controller;

import com.downtowncafe.cafemanagement.dto.MenuItemDTO;
import com.downtowncafe.cafemanagement.dto.MenuResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    private final Map<String, List<MenuItemDTO>> menuItems = new ConcurrentHashMap<>();

    public MenuController() {
        initializeMenu();
    }

    @GetMapping("/menu")
    public MenuResponseDTO getMenu() {
        Map<String, String> experience = Map.of(
                "signatureBlends", "Explore our world of artisan coffees and signature teas.",
                "chefsSpecials", "A culinary journey from traditional Arabic breakfasts to Western classics.",
                "artisanPastries", "End your meal with our decadent homemade desserts and shisha.");
        return new MenuResponseDTO(menuItems, experience);
    }

    @PostMapping("/menu")
    public MenuItemDTO addMenuItem(@RequestBody MenuItemRequest request) {
        String category = request.category();
        MenuItemDTO newItem = new MenuItemDTO(
                UUID.randomUUID().toString(),
                request.name(),
                request.description() != null ? request.description() : "",
                request.price(),
                request.subCategory() != null ? request.subCategory() : "General");

        menuItems.computeIfAbsent(category, k -> new CopyOnWriteArrayList<>()).add(newItem);
        return newItem;
    }

    public record MenuItemRequest(String name, String category, String description, BigDecimal price,
            String subCategory) {
    }

    private void initializeMenu() {
        // Coffee & Tea
        menuItems.put("Coffee & Tea", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("hc1", "Espresso Single", "", new BigDecimal("18"), "Hot Coffee"),
                new MenuItemDTO("hc2", "Espresso Double", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc3", "Americano", "", new BigDecimal("22"), "Black Coffee"),
                new MenuItemDTO("hc4", "Cappuccino", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc5", "Cafe Latte", "", new BigDecimal("22"), "Hot Coffee")
        // (Shortened for brevity but keeping structure)
        )));
        // Add other categories...
        menuItems.put("Main Courses", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("mn1", "Beef Steak", "", new BigDecimal("90"), "Entrees"),
                new MenuItemDTO("mn2", "Grilled Salmon", "", new BigDecimal("80"), "Entrees"))));
    }
}
