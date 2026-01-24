package com.downtowncafe.controller;

import com.downtowncafe.dto.MenuItemDTO;
import com.downtowncafe.dto.MenuResponseDTO;
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
                                "chefsSpecials",
                                "A culinary journey from traditional Arabic breakfasts to Western classics.",
                                "artisanPastries", "End your meal with our decadent homemade desserts and shisha.");
                return new MenuResponseDTO(menuItems, experience);
        }

        @PostMapping("/menu")
        public MenuItemDTO addMenuItem(@RequestBody MenuItemRequest request) {
                String category = request.category().toLowerCase();
                MenuItemDTO newItem = new MenuItemDTO(
                                UUID.randomUUID().toString(),
                                request.name(),
                                request.description() != null ? request.description() : "",
                                request.price());

                menuItems.computeIfAbsent(category, k -> new CopyOnWriteArrayList<>()).add(newItem);
                return newItem;
        }

        public record MenuItemRequest(String name, String category, String description, BigDecimal price) {
        }

        private void initializeMenu() {
                // Coffee & Tea
                menuItems.put("Coffee & Tea", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("hc1", "Espresso Single", "", new BigDecimal("18")),
                                new MenuItemDTO("hc2", "Espresso Double", "", new BigDecimal("22")),
                                new MenuItemDTO("hc3", "Macchiato", "", new BigDecimal("22")),
                                new MenuItemDTO("hc4", "Flat White", "", new BigDecimal("22")),
                                new MenuItemDTO("hc5", "Piccolo", "", new BigDecimal("22")),
                                new MenuItemDTO("hc6", "Cappuccino", "", new BigDecimal("22")),
                                new MenuItemDTO("hc7", "Cafe Latte", "", new BigDecimal("22")),
                                new MenuItemDTO("hc8", "Cafe Mocha", "", new BigDecimal("25")),
                                new MenuItemDTO("hc9", "Spanish Latte", "", new BigDecimal("25")),
                                new MenuItemDTO("hc10", "Caramel Macchiato", "", new BigDecimal("25")),
                                new MenuItemDTO("hc11", "Hot Chocolate", "", new BigDecimal("25")),
                                new MenuItemDTO("hc12", "Matcha Green Tea Latte", "", new BigDecimal("25")),
                                new MenuItemDTO("bc1", "Americano", "", new BigDecimal("22")),
                                new MenuItemDTO("bc2", "Turkish Coffee", "", new BigDecimal("20")),
                                new MenuItemDTO("ic1", "Iced Americano", "", new BigDecimal("22")),
                                new MenuItemDTO("ic2", "Iced Brew", "", new BigDecimal("25")),
                                new MenuItemDTO("ic3", "Iced Cappuccino", "", new BigDecimal("22")),
                                new MenuItemDTO("ic4", "Iced Cafe Latte", "", new BigDecimal("22")),
                                new MenuItemDTO("ic5", "Iced Cafe Mocha", "", new BigDecimal("25")),
                                new MenuItemDTO("ic6", "Iced Spanish Latte", "", new BigDecimal("25")),
                                new MenuItemDTO("ic7", "Iced Caramel Macchiato", "", new BigDecimal("25")),
                                new MenuItemDTO("ic8", "Ice Shaken Espresso", "", new BigDecimal("25")),
                                new MenuItemDTO("ic9", "Iced Flat White", "", new BigDecimal("22")),
                                new MenuItemDTO("ic10", "Affogato", "", new BigDecimal("25")),
                                new MenuItemDTO("t1", "English Breakfast", "", new BigDecimal("22")),
                                new MenuItemDTO("t2", "Earl Gray", "", new BigDecimal("22")),
                                new MenuItemDTO("t3", "Green Tea", "", new BigDecimal("20")),
                                new MenuItemDTO("t4", "Chamomile", "", new BigDecimal("22")),
                                new MenuItemDTO("t5", "Lipton Tea", "", new BigDecimal("20")),
                                new MenuItemDTO("t6", "Jasmine Pearl", "", new BigDecimal("25")),
                                new MenuItemDTO("it1", "Peach Iced Tea", "", new BigDecimal("25")),
                                new MenuItemDTO("it2", "Lemon Iced Tea", "", new BigDecimal("25")),
                                new MenuItemDTO("add1", "Add-on Syrup", "Vanilla, Caramel, Hazelnut, White Chocolate",
                                                new BigDecimal("5")))));

                // Cold Beverages
                menuItems.put("Cold Beverages", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("j1", "Fresh Juices",
                                                "Orange, Pineapple, Sweet Melon, Lemon/Mint, Watermelon, Grapefruit, Kiwi, Apple, Mango, Strawberry, Carrot, Cocktail",
                                                new BigDecimal("20")), // Range 20-22, simplified base
                                new MenuItemDTO("fb1", "Fresh Blends",
                                                "Healthy Mix, Detox Mix, Magic Mango, Orange Kicker, Mint Hint, Red Notice, Watermelon Rose, Ultimate Green",
                                                new BigDecimal("25")),
                                new MenuItemDTO("mk1", "Mocktails",
                                                "Mojito, Sunrise, Strawberry Fresco, Ibrik Mule, Blue Lagoon, Rose, Colada",
                                                new BigDecimal("25")),
                                new MenuItemDTO("ms1", "Milkshakes & Smoothies",
                                                "Strawberry, Vanilla, Pistachio, Chocolate, Oreo, Nutella, Avocado",
                                                new BigDecimal("25")),
                                new MenuItemDTO("fp1", "Frappuccino",
                                                "Classic, Caramel, Nutella, Coffee, Double Shot, Chocolate Chip, Matcha",
                                                new BigDecimal("22")), // Base price
                                new MenuItemDTO("w1", "Local Water", "", new BigDecimal("7")),
                                new MenuItemDTO("w2", "Perrier", "", new BigDecimal("15")),
                                new MenuItemDTO("w3", "Soda Water", "", new BigDecimal("12")),
                                new MenuItemDTO("w4", "Coca-Cola Products", "", new BigDecimal("10")),
                                new MenuItemDTO("w5", "Red Bull", "", new BigDecimal("25")))));

                // Breakfast
                menuItems.put("Breakfast", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("bk1", "Egg Selection",
                                                "Three eggs: Scrambled, fried, boiled, shakshuka, sunny side up, omelette, or poached",
                                                new BigDecimal("35")),
                                new MenuItemDTO("bk2", "Arabic Breakfast", "", new BigDecimal("80")),
                                new MenuItemDTO("bk3", "English Breakfast", "", new BigDecimal("70")),
                                new MenuItemDTO("bk4", "Continental Breakfast", "", new BigDecimal("60")),
                                new MenuItemDTO("sd1", "Hummus", "", new BigDecimal("30")),
                                new MenuItemDTO("sd2", "Labneh", "", new BigDecimal("30")),
                                new MenuItemDTO("sd3", "Grilled Halloumi", "", new BigDecimal("35")),
                                new MenuItemDTO("sd4", "Pancake or French Toast", "", new BigDecimal("35")),
                                new MenuItemDTO("sd5", "Pastry Basket", "", new BigDecimal("45")))));

                // Soups, Salads & Appetizers
                menuItems.put("Soups, Salads & Apps", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("sp1", "Soups",
                                                "French Onion, Lentil, Tomato, Mushroom, Chicken Clear, Vegetable, Seafood",
                                                new BigDecimal("20")), // Base price
                                new MenuItemDTO("ap1", "Prawns Tempura", "", new BigDecimal("65")),
                                new MenuItemDTO("ap2", "Buffalo Wings", "", new BigDecimal("45")),
                                new MenuItemDTO("ap3", "Crispy Chicken Bites", "", new BigDecimal("45")),
                                new MenuItemDTO("ap4", "Vegetable Spring Rolls", "", new BigDecimal("40")),
                                new MenuItemDTO("ap5", "Arabic Hot Mezzah", "", new BigDecimal("40")),
                                new MenuItemDTO("sl1", "Smoked Salmon Platter", "", new BigDecimal("65")),
                                new MenuItemDTO("sl2", "Smoked Salmon Nicoise", "", new BigDecimal("55")),
                                new MenuItemDTO("sl3", "Caesar Salad", "", new BigDecimal("45")),
                                new MenuItemDTO("sl4", "Greek Salad", "", new BigDecimal("45")),
                                new MenuItemDTO("sl5", "Grilled Pineapple Avocado", "", new BigDecimal("45")),
                                new MenuItemDTO("sl6", "Arabic Cold Mezzah", "", new BigDecimal("40")),
                                new MenuItemDTO("sl7", "Green Salad", "", new BigDecimal("35")),
                                new MenuItemDTO("sl8", "Rocca Salad", "", new BigDecimal("35")))));

                // Main Courses
                menuItems.put("Main Courses", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("mn1", "Beef Steak", "", new BigDecimal("90")),
                                new MenuItemDTO("mn2", "Grilled Salmon", "", new BigDecimal("80")),
                                new MenuItemDTO("mn3", "Arabic Mix Grill", "", new BigDecimal("70")),
                                new MenuItemDTO("mn4", "Grilled Hammour", "", new BigDecimal("70")),
                                new MenuItemDTO("mn5", "Baby Chicken", "", new BigDecimal("55")),
                                new MenuItemDTO("mn6", "Nasi Goreng", "", new BigDecimal("55")),
                                new MenuItemDTO("mn7", "Curry", "", new BigDecimal("50")),
                                new MenuItemDTO("mn8", "Biryani", "", new BigDecimal("45")),
                                new MenuItemDTO("sw1", "Steak Sandwich", "", new BigDecimal("55")),
                                new MenuItemDTO("sw2", "Club Sandwich", "", new BigDecimal("45")),
                                new MenuItemDTO("sw3", "Downtown Roast Beef", "", new BigDecimal("45")),
                                new MenuItemDTO("sw4", "Warm Chicken", "", new BigDecimal("45")),
                                new MenuItemDTO("sw5", "Chicken Burger", "", new BigDecimal("40")),
                                new MenuItemDTO("sw6", "Beef Burger", "", new BigDecimal("50")),
                                new MenuItemDTO("sw7", "Chicken Fajita", "", new BigDecimal("35")),
                                new MenuItemDTO("sw8", "Grilled Halloumi Sandwich", "", new BigDecimal("35")),
                                new MenuItemDTO("sw9", "Vegetable & Cheese", "", new BigDecimal("35")),
                                new MenuItemDTO("ps1", "Pasta Carbonara/Bolognese/Arrabbiata", "",
                                                new BigDecimal("50")),
                                new MenuItemDTO("ps2", "Pasta Pesto", "", new BigDecimal("55")),
                                new MenuItemDTO("pz1", "Pizza Margherita", "", new BigDecimal("30")),
                                new MenuItemDTO("pz2", "Pizza Pepperoni/Frutti Di Mare", "", new BigDecimal("40")))));

                // Desserts & Shisha
                menuItems.put("Desserts & Shisha", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("d1", "Tiramisu", "", new BigDecimal("30")),
                                new MenuItemDTO("d2", "Cheese Cake", "", new BigDecimal("22")),
                                new MenuItemDTO("d3", "Um Ali", "", new BigDecimal("20")),
                                new MenuItemDTO("d4", "1kg Cake", "Various Flavors", new BigDecimal("120")),
                                new MenuItemDTO("sh1", "Classic Shisha", "", new BigDecimal("50")),
                                new MenuItemDTO("sh2", "Double Flavors Shisha", "", new BigDecimal("55")),
                                new MenuItemDTO("sh3", "Triple Flavors Shisha", "", new BigDecimal("60")))));
        }
}
