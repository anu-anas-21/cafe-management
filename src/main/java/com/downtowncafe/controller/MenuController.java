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
                                // Hot Coffee
                                new MenuItemDTO("hc1", "Espresso Single", "", new BigDecimal("18"), "Hot Coffee"),
                                new MenuItemDTO("hc2", "Espresso Double", "", new BigDecimal("22"), "Hot Coffee"),
                                new MenuItemDTO("hc3", "Macchiato", "", new BigDecimal("22"), "Hot Coffee"),
                                new MenuItemDTO("hc4", "Flat White", "", new BigDecimal("22"), "Hot Coffee"),
                                new MenuItemDTO("hc5", "Piccolo", "", new BigDecimal("22"), "Hot Coffee"),
                                new MenuItemDTO("hc6", "Cappuccino", "", new BigDecimal("22"), "Hot Coffee"),
                                new MenuItemDTO("hc7", "Cafe Latte", "", new BigDecimal("22"), "Hot Coffee"),
                                new MenuItemDTO("hc8", "Cafe Mocha", "", new BigDecimal("25"), "Hot Coffee"),
                                new MenuItemDTO("hc9", "Spanish Latte", "", new BigDecimal("25"), "Hot Coffee"),
                                new MenuItemDTO("hc10", "Caramel Macchiato", "", new BigDecimal("25"), "Hot Coffee"),
                                new MenuItemDTO("hc11", "Hot Chocolate", "", new BigDecimal("25"), "Hot Coffee"),
                                new MenuItemDTO("hc12", "Matcha Green Tea Latte", "", new BigDecimal("25"),
                                                "Hot Coffee"),

                                // Black Coffee
                                new MenuItemDTO("bc1", "Americano", "", new BigDecimal("22"), "Black Coffee"),
                                new MenuItemDTO("bc2", "Turkish Coffee", "", new BigDecimal("20"), "Black Coffee"),

                                // Iced Coffee
                                new MenuItemDTO("ic1", "Iced Americano", "", new BigDecimal("22"), "Iced Coffee"),
                                new MenuItemDTO("ic2", "Iced Brew", "", new BigDecimal("25"), "Iced Coffee"),
                                new MenuItemDTO("ic3", "Iced Cappuccino", "", new BigDecimal("22"), "Iced Coffee"),
                                new MenuItemDTO("ic4", "Iced Cafe Latte", "", new BigDecimal("22"), "Iced Coffee"),
                                new MenuItemDTO("ic5", "Iced Cafe Mocha", "", new BigDecimal("25"), "Iced Coffee"),
                                new MenuItemDTO("ic6", "Iced Spanish Latte", "", new BigDecimal("25"), "Iced Coffee"),
                                new MenuItemDTO("ic7", "Iced Caramel Macchiato", "", new BigDecimal("25"),
                                                "Iced Coffee"),
                                new MenuItemDTO("ic8", "Ice Shaken Espresso", "", new BigDecimal("25"), "Iced Coffee"),
                                new MenuItemDTO("ic9", "Iced Flat White", "", new BigDecimal("22"), "Iced Coffee"),
                                new MenuItemDTO("ic10", "Affogato", "", new BigDecimal("25"), "Iced Coffee"),

                                // Hot Tea
                                new MenuItemDTO("t1", "English Breakfast", "", new BigDecimal("22"), "Hot Tea"),
                                new MenuItemDTO("t2", "Earl Gray", "", new BigDecimal("22"), "Hot Tea"),
                                new MenuItemDTO("t3", "Green Tea", "", new BigDecimal("20"), "Hot Tea"),
                                new MenuItemDTO("t4", "Chamomile", "", new BigDecimal("22"), "Hot Tea"),
                                new MenuItemDTO("t5", "Lipton Tea", "", new BigDecimal("20"), "Hot Tea"),
                                new MenuItemDTO("t6", "Jasmine Pearl", "", new BigDecimal("25"), "Hot Tea"),

                                // Iced Tea
                                new MenuItemDTO("it1", "Peach", "", new BigDecimal("25"), "Iced Tea"),
                                new MenuItemDTO("it2", "Lemon", "", new BigDecimal("25"), "Iced Tea"),

                                // Add-ons
                                new MenuItemDTO("add1", "Vanilla Syrup", "", new BigDecimal("5"), "Add-ons"),
                                new MenuItemDTO("add2", "Caramel Syrup", "", new BigDecimal("5"), "Add-ons"),
                                new MenuItemDTO("add3", "Hazelnut Syrup", "", new BigDecimal("5"), "Add-ons"),
                                new MenuItemDTO("add4", "White Chocolate Syrup", "", new BigDecimal("5"), "Add-ons"))));

                // Cold Beverages
                menuItems.put("Cold Beverages", new CopyOnWriteArrayList<>(List.of(
                                // Fresh Juices
                                new MenuItemDTO("j1", "Orange", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j2", "Pineapple", "", new BigDecimal("22"), "Fresh Juices"),
                                new MenuItemDTO("j3", "Sweet Melon", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j4", "Lemon/Mint", "", new BigDecimal("22"), "Fresh Juices"),
                                new MenuItemDTO("j5", "Watermelon", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j6", "Grapefruit", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j7", "Kiwi", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j8", "Apple", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j9", "Mango", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j10", "Strawberry", "", new BigDecimal("22"), "Fresh Juices"),
                                new MenuItemDTO("j11", "Carrot", "", new BigDecimal("20"), "Fresh Juices"),
                                new MenuItemDTO("j12", "Cocktail", "", new BigDecimal("22"), "Fresh Juices"),

                                // Fresh Blends
                                new MenuItemDTO("fb1", "Healthy Mix", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb2", "Detox Mix", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb3", "Magic Mango", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb4", "Orange Kicker", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb5", "Mint Hint", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb6", "Red Notice", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb7", "Watermelon Rose", "", new BigDecimal("25"), "Fresh Blends"),
                                new MenuItemDTO("fb8", "Ultimate Green", "", new BigDecimal("25"), "Fresh Blends"),

                                // Mocktails
                                new MenuItemDTO("mk1", "Mojito", "Classic, Passion Fruit, Peach, Strawberry",
                                                new BigDecimal("25"), "Mocktails"),
                                new MenuItemDTO("mk2", "Sunrise", "", new BigDecimal("25"), "Mocktails"),
                                new MenuItemDTO("mk3", "Strawberry Fresco", "", new BigDecimal("25"), "Mocktails"),
                                new MenuItemDTO("mk4", "Ibrik Mule", "", new BigDecimal("25"), "Mocktails"),
                                new MenuItemDTO("mk5", "Blue Lagoon", "", new BigDecimal("25"), "Mocktails"),
                                new MenuItemDTO("mk6", "Rose", "", new BigDecimal("25"), "Mocktails"),
                                new MenuItemDTO("mk7", "Colada", "", new BigDecimal("25"), "Mocktails"),

                                // Milkshakes
                                new MenuItemDTO("ms1", "Strawberry Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),
                                new MenuItemDTO("ms2", "Vanilla Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),
                                new MenuItemDTO("ms3", "Pistachio Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),
                                new MenuItemDTO("ms4", "Chocolate Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),
                                new MenuItemDTO("ms5", "Oreo Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),
                                new MenuItemDTO("ms6", "Nutella Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),
                                new MenuItemDTO("ms7", "Avocado Milkshake", "", new BigDecimal("25"),
                                                "Milkshakes & Smoothies"),

                                // Frappuccino
                                new MenuItemDTO("fp1", "Classic Frappuccino", "", new BigDecimal("22"), "Frappuccino"),
                                new MenuItemDTO("fp2", "Caramel Frappuccino", "", new BigDecimal("25"), "Frappuccino"),
                                new MenuItemDTO("fp3", "Nutella Frappuccino", "", new BigDecimal("25"), "Frappuccino"),
                                new MenuItemDTO("fp4", "Coffee Frappuccino", "", new BigDecimal("25"), "Frappuccino"),
                                new MenuItemDTO("fp5", "Double Shot Frappuccino", "", new BigDecimal("25"),
                                                "Frappuccino"),
                                new MenuItemDTO("fp6", "Chocolate Chip Frappuccino", "", new BigDecimal("25"),
                                                "Frappuccino"),
                                new MenuItemDTO("fp7", "Matcha Frappuccino", "", new BigDecimal("25"), "Frappuccino"),

                                // Water & Soft Drinks
                                new MenuItemDTO("w1", "Local Water", "", new BigDecimal("7"), "Water & Soft Drinks"),
                                new MenuItemDTO("w2", "Perrier", "", new BigDecimal("15"), "Water & Soft Drinks"),
                                new MenuItemDTO("w3", "Soda Water", "", new BigDecimal("12"), "Water & Soft Drinks"),
                                new MenuItemDTO("w4", "Coca-Cola products", "", new BigDecimal("10"),
                                                "Water & Soft Drinks"),
                                new MenuItemDTO("w5", "Red Bull", "", new BigDecimal("25"), "Water & Soft Drinks"))));

                // Breakfast
                menuItems.put("Breakfast", new CopyOnWriteArrayList<>(List.of(
                                // Egg Selection
                                new MenuItemDTO("bk1", "Egg Selection",
                                                "Three eggs prepared as: Scrambled, fried, boiled, shakshuka, sunny side up, omelette, or poached.",
                                                new BigDecimal("35"), "Egg Selection"),

                                // Set Breakfasts
                                new MenuItemDTO("bk2", "Arabic Breakfast", "", new BigDecimal("80"), "Set Breakfasts"),
                                new MenuItemDTO("bk3", "English Breakfast", "", new BigDecimal("70"), "Set Breakfasts"),
                                new MenuItemDTO("bk4", "Continental Breakfast", "", new BigDecimal("60"),
                                                "Set Breakfasts"),

                                // Sides
                                new MenuItemDTO("sd1", "Hummus", "", new BigDecimal("30"), "Sides"),
                                new MenuItemDTO("sd2", "Labneh", "", new BigDecimal("30"), "Sides"),
                                new MenuItemDTO("sd3", "Grilled Halloumi", "", new BigDecimal("35"), "Sides"),
                                new MenuItemDTO("sd4", "Pancake or French Toast", "", new BigDecimal("35"), "Sides"),
                                new MenuItemDTO("sd5", "Pastry Basket", "", new BigDecimal("45"), "Sides"))));

                // Soups, Salads & Appetizers
                menuItems.put("Soups, Salads & Apps", new CopyOnWriteArrayList<>(List.of(
                                // Soups
                                new MenuItemDTO("sp1", "French Onion Soup", "", new BigDecimal("30"), "Soups"),
                                new MenuItemDTO("sp2", "Lentil Soup", "", new BigDecimal("25"), "Soups"),
                                new MenuItemDTO("sp3", "Tomato Soup", "", new BigDecimal("20"), "Soups"),
                                new MenuItemDTO("sp4", "Mushroom Soup", "", new BigDecimal("25"), "Soups"),
                                new MenuItemDTO("sp5", "Chicken Clear Soup", "", new BigDecimal("25"), "Soups"),
                                new MenuItemDTO("sp6", "Vegetable Soup", "", new BigDecimal("20"), "Soups"),
                                new MenuItemDTO("sp7", "Seafood Soup", "", new BigDecimal("30"), "Soups"),

                                // Appetizers
                                new MenuItemDTO("ap1", "Prawns Tempura", "", new BigDecimal("65"), "Appetizers"),
                                new MenuItemDTO("ap2", "Buffalo Wings", "", new BigDecimal("45"), "Appetizers"),
                                new MenuItemDTO("ap3", "Crispy Chicken Bites", "", new BigDecimal("45"), "Appetizers"),
                                new MenuItemDTO("ap4", "Vegetable Spring Rolls", "", new BigDecimal("40"),
                                                "Appetizers"),
                                new MenuItemDTO("ap5", "Arabic Hot Mezzah", "", new BigDecimal("40"), "Appetizers"),

                                // Salads
                                new MenuItemDTO("sl1", "Smoked Salmon Platter", "", new BigDecimal("65"), "Salads"),
                                new MenuItemDTO("sl2", "Smoked Salmon Nicoise", "", new BigDecimal("55"), "Salads"),
                                new MenuItemDTO("sl3", "Caesar", "", new BigDecimal("45"), "Salads"),
                                new MenuItemDTO("sl4", "Greek", "", new BigDecimal("45"), "Salads"),
                                new MenuItemDTO("sl5", "Grilled Pineapple Avocado", "", new BigDecimal("45"), "Salads"),
                                new MenuItemDTO("sl6", "Arabic Cold Mezzah", "", new BigDecimal("40"), "Salads"),
                                new MenuItemDTO("sl7", "Green Salad", "", new BigDecimal("35"), "Salads"),
                                new MenuItemDTO("sl8", "Rocca Salad", "", new BigDecimal("35"), "Salads"))));

                // Main Courses
                menuItems.put("Main Courses", new CopyOnWriteArrayList<>(List.of(
                                // Entrees
                                new MenuItemDTO("mn1", "Beef Steak", "", new BigDecimal("90"), "Entrees"),
                                new MenuItemDTO("mn2", "Grilled Salmon", "", new BigDecimal("80"), "Entrees"),
                                new MenuItemDTO("mn3", "Arabic Mix Grill", "", new BigDecimal("70"), "Entrees"),
                                new MenuItemDTO("mn4", "Grilled Hammour", "", new BigDecimal("70"), "Entrees"),
                                new MenuItemDTO("mn5", "Baby Chicken", "", new BigDecimal("55"), "Entrees"),
                                new MenuItemDTO("mn6", "Nasi Goreng", "", new BigDecimal("55"), "Entrees"),
                                new MenuItemDTO("mn7", "Curry", "", new BigDecimal("50"), "Entrees"),
                                new MenuItemDTO("mn8", "Biryani", "", new BigDecimal("45"), "Entrees"),

                                // Sandwiches
                                new MenuItemDTO("sw1", "Steak Sandwich", "", new BigDecimal("55"), "Sandwiches"),
                                new MenuItemDTO("sw2", "Club Sandwich", "", new BigDecimal("45"), "Sandwiches"),
                                new MenuItemDTO("sw3", "Downtown Roast Beef", "", new BigDecimal("45"), "Sandwiches"),
                                new MenuItemDTO("sw4", "Warm Chicken", "", new BigDecimal("45"), "Sandwiches"),
                                new MenuItemDTO("sw5", "Chicken Burger", "", new BigDecimal("40"), "Sandwiches"),
                                new MenuItemDTO("sw6", "Beef Burger", "", new BigDecimal("50"), "Sandwiches"),
                                new MenuItemDTO("sw7", "Chicken Fajita", "", new BigDecimal("35"), "Sandwiches"),
                                new MenuItemDTO("sw8", "Grilled Halloumi", "", new BigDecimal("35"), "Sandwiches"),
                                new MenuItemDTO("sw9", "Vegetable & Cheese", "", new BigDecimal("35"), "Sandwiches"),

                                // Pasta & Pizza
                                new MenuItemDTO("ps1", "Carbonara", "", new BigDecimal("50"), "Pasta & Pizza"),
                                new MenuItemDTO("ps2", "Bolognese", "", new BigDecimal("50"), "Pasta & Pizza"),
                                new MenuItemDTO("ps3", "Arrabbiata", "", new BigDecimal("50"), "Pasta & Pizza"),
                                new MenuItemDTO("ps4", "Pesto", "", new BigDecimal("55"), "Pasta & Pizza"),
                                new MenuItemDTO("pz1", "Margherita", "", new BigDecimal("30"), "Pasta & Pizza"),
                                new MenuItemDTO("pz2", "Pepperoni", "", new BigDecimal("40"), "Pasta & Pizza"),
                                new MenuItemDTO("pz3", "Frutti Di Mare", "", new BigDecimal("55"), "Pasta & Pizza"))));

                // Desserts & Shisha
                menuItems.put("Desserts & Shisha", new CopyOnWriteArrayList<>(List.of(
                                // Cakes & Sweets
                                new MenuItemDTO("d1", "Tiramisu", "", new BigDecimal("30"), "Cakes & Sweets"),
                                new MenuItemDTO("d2", "Cheese Cake", "", new BigDecimal("22"), "Cakes & Sweets"),
                                new MenuItemDTO("d3", "Um Ali", "", new BigDecimal("20"), "Cakes & Sweets"),
                                new MenuItemDTO("d4", "1kg Cake", "Various Flavors", new BigDecimal("120"),
                                                "Cakes & Sweets"),

                                // Shisha
                                new MenuItemDTO("sh1", "Classic Shisha", "", new BigDecimal("50"), "Shisha"),
                                new MenuItemDTO("sh2", "Double Flavors",
                                                "Double Apple, Watermelon, Blueberry, Orange, Lemon, Grape, Mint",
                                                new BigDecimal("55"), "Shisha"),
                                new MenuItemDTO("sh3", "Triple Flavors", "", new BigDecimal("60"), "Shisha"))));
        }
}
