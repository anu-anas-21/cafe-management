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

        // Using ConcurrentHashMap and CopyOnWriteArrayList for thread-safety and
        // mutability
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

        // Helper DTO for incoming requests
        public record MenuItemRequest(String name, String category, String description, BigDecimal price) {
        }

        private void initializeMenu() {
                // 1. Hot Coffee
                menuItems.put("hot coffee", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("hc1", "Americano", "", new BigDecimal("20")),
                                new MenuItemDTO("hc2", "Turkish Coffee", "", new BigDecimal("22")),
                                new MenuItemDTO("hc3", "Single Espresso", "", new BigDecimal("18")),
                                new MenuItemDTO("hc4", "Double Espresso", "", new BigDecimal("22")),
                                new MenuItemDTO("hc5", "Macchiato", "", new BigDecimal("22")),
                                new MenuItemDTO("hc6", "Flat White", "", new BigDecimal("22")),
                                new MenuItemDTO("hc7", "Piccolo", "", new BigDecimal("22")),
                                new MenuItemDTO("hc8", "Cappuccino", "", new BigDecimal("22")),
                                new MenuItemDTO("hc9", "Cafe Latte", "", new BigDecimal("25")),
                                new MenuItemDTO("hc10", "Cafe Mocha", "", new BigDecimal("25")),
                                new MenuItemDTO("hc11", "Spanish Latte", "", new BigDecimal("25")),
                                new MenuItemDTO("hc12", "Caramel Macchiato", "", new BigDecimal("25")),
                                new MenuItemDTO("hc13", "Hot Chocolate", "", new BigDecimal("25")),
                                new MenuItemDTO("hc14", "Matcha Green Tea Latte", "", new BigDecimal("25")))));

                // 2. Iced Coffee
                menuItems.put("iced coffee", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("ic1", "Iced Americano", "", new BigDecimal("22")),
                                new MenuItemDTO("ic2", "Iced Brew", "", new BigDecimal("25")),
                                new MenuItemDTO("ic3", "Iced Cappuccino", "", new BigDecimal("22")),
                                new MenuItemDTO("ic4", "Iced Cafe Latte", "", new BigDecimal("22")),
                                new MenuItemDTO("ic5", "Iced Cafe Mocha", "", new BigDecimal("25")),
                                new MenuItemDTO("ic6", "Iced Spanish Latte", "", new BigDecimal("25")),
                                new MenuItemDTO("ic7", "Iced Caramel Macchiato", "", new BigDecimal("25")),
                                new MenuItemDTO("ic8", "D Ice Shaken Espresso", "", new BigDecimal("25")),
                                new MenuItemDTO("ic9", "Iced Flat White", "", new BigDecimal("22")),
                                new MenuItemDTO("ic10", "Affogato", "", new BigDecimal("25")))));

                // 3. Tea
                menuItems.put("tea", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("t1", "English Breakfast", "", new BigDecimal("22")),
                                new MenuItemDTO("t2", "Earl Gray", "", new BigDecimal("22")),
                                new MenuItemDTO("t3", "Green Tea", "", new BigDecimal("20")),
                                new MenuItemDTO("t4", "Chamomile", "", new BigDecimal("22")),
                                new MenuItemDTO("t5", "Lipton Tea", "", new BigDecimal("20")),
                                new MenuItemDTO("t6", "Jasmine Pearl", "", new BigDecimal("25")),
                                new MenuItemDTO("t7", "Peach Ice Tea", "", new BigDecimal("25")),
                                new MenuItemDTO("t8", "Lemon Ice Tea", "", new BigDecimal("25")))));

                // 4. Mojitos & Mocktails
                menuItems.put("mojitos & mocktails", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("m1", "Mojito", "Classic, Passion Fruit, Peach, Strawberry",
                                                new BigDecimal("25")),
                                new MenuItemDTO("m2", "Sunrise", "Orange Juice, Grenadine Syrup, sprite",
                                                new BigDecimal("25")),
                                new MenuItemDTO("m3", "Strawberry Fresco", "Strawberry Puree, Sprite",
                                                new BigDecimal("25")),
                                new MenuItemDTO("m4", "Ibrik Mule", "Strawberry, Ginger Ale, Rosemary, Ginger Juice",
                                                new BigDecimal("25")),
                                new MenuItemDTO("m5", "Blue Lagoon", "Lemon Juice, Sprite, Blue coracco",
                                                new BigDecimal("25")),
                                new MenuItemDTO("m6", "Rose", "Grape Fruit, Rose Syrup, Soda water",
                                                new BigDecimal("25")),
                                new MenuItemDTO("m7", "Colada", "Pineapple, Coconut Syrup, Whipping Cream",
                                                new BigDecimal("25")))));

                // 5. Fresh Juice & Healthy Mix
                menuItems.put("fresh juice & healthy", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("j1", "Orange", "", new BigDecimal("20")),
                                new MenuItemDTO("j2", "Pineapple", "", new BigDecimal("22")),
                                new MenuItemDTO("j3", "Sweetmwlon", "", new BigDecimal("20")),
                                new MenuItemDTO("j4", "Grapefruit", "", new BigDecimal("20")),
                                new MenuItemDTO("j5", "Lemon/Mint", "", new BigDecimal("22")),
                                new MenuItemDTO("j6", "Watermelon", "", new BigDecimal("20")),
                                new MenuItemDTO("j7", "Kiwi", "", new BigDecimal("20")),
                                new MenuItemDTO("j8", "Apple", "", new BigDecimal("20")),
                                new MenuItemDTO("j9", "Mango", "", new BigDecimal("20")),
                                new MenuItemDTO("j10", "Strawberry", "", new BigDecimal("22")),
                                new MenuItemDTO("j11", "Carrot", "", new BigDecimal("20")),
                                new MenuItemDTO("j12", "Cocktail", "", new BigDecimal("22")),
                                new MenuItemDTO("hm1", "Healthy Mix", "Avocado, Basil, Ginger, Mint, Lemon Juice",
                                                new BigDecimal("35")),
                                new MenuItemDTO("hm2", "Detox Mix", "Basil, Cucumber, lemon Juice, Ginger",
                                                new BigDecimal("30")),
                                new MenuItemDTO("hm3", "Magic Mango", "Mango, Banana, Lemon, Coconut Milk, Mint",
                                                new BigDecimal("30")),
                                new MenuItemDTO("hm4", "Orange Kicker", "Orange Juice, Carrot, Fresh Ginger",
                                                new BigDecimal("35")),
                                new MenuItemDTO("hm5", "Mint Hint", "Mint, Green Apple, lemon Juice",
                                                new BigDecimal("35")),
                                new MenuItemDTO("hm6", "Red Notice", "Beetroot, Watermelon", new BigDecimal("25")),
                                new MenuItemDTO("hm7", "Watermelon Rose", "Watermelon, Rose Syrup",
                                                new BigDecimal("45")),
                                new MenuItemDTO("hm8", "Ultimate Green", "Mint, Lemon Juice, Sugar Syrup",
                                                new BigDecimal("60")))));

                // 6. Frappes & Shakes
                menuItems.put("frappes & shakes", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("f1", "Classic Frappe", "", new BigDecimal("22")),
                                new MenuItemDTO("f2", "Caramel Frappe", "", new BigDecimal("25")),
                                new MenuItemDTO("f3", "Nutella Frappe", "", new BigDecimal("25")),
                                new MenuItemDTO("f4", "Coffee Frappe", "", new BigDecimal("25")),
                                new MenuItemDTO("f5", "Double shot Frappe", "", new BigDecimal("25")),
                                new MenuItemDTO("f6", "Chocolate chip Frappe", "", new BigDecimal("25")),
                                new MenuItemDTO("f7", "Matcha Green Tea Frappe", "", new BigDecimal("25")),
                                new MenuItemDTO("s1", "Strawberry Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s2", "Blueberry Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s3", "Mango Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s4", "Pina colada Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s5", "Chocolate chips Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s6", "Oreo Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s7", "Mix nuts Milkshake", "", new BigDecimal("25")),
                                new MenuItemDTO("s8", "Fruity Milkshake", "", new BigDecimal("25")))));

                // 7. Breakfast
                menuItems.put("breakfast", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("bk1", "Three eggs selection",
                                                "Scrambled, fried, boiled, shakshuka, sunny side up, omelette or poached. Served with butter, jam, arabic or toast bread, sausage, grilled tomato and hash brown potato",
                                                new BigDecimal("35")),
                                new MenuItemDTO("bk2", "Hummus with olive oil",
                                                "Served with arabic bread, cucumber, tomato and pickles",
                                                new BigDecimal("25")),
                                new MenuItemDTO("bk3", "Labneh with olive oil",
                                                "Served with arabic bread, cucumber, tomato and pickles",
                                                new BigDecimal("25")),
                                new MenuItemDTO("bk4", "Grilled halloumi cheese",
                                                "Served with arabic bread, cucumber, tomato and pickles",
                                                new BigDecimal("25")),
                                new MenuItemDTO("bk5", "Pan cake or french toast",
                                                "Served with honey or syrup and whipped cream", new BigDecimal("25")),
                                new MenuItemDTO("bk6", "Cereals", "Served with honey and milk", new BigDecimal("25")),
                                new MenuItemDTO("bk7", "Pastry Basket",
                                                "Croissants, muffins and danish pastries with jam and butter",
                                                new BigDecimal("20")),
                                new MenuItemDTO("bk8", "Continental breakfast",
                                                "Freshly squeezed seasonal fruit juices, morning bakeries, freshly sliced fruits, fruit yogurt, toast bread with jam and butter, cereal, brewed coffee, Tea or hot chocolate",
                                                new BigDecimal("30")),
                                new MenuItemDTO("bk9", "English breakfast",
                                                "Freshly squeezed seasonal fruit juices, morning bakeries, freshly sliced fruits, cereal, toast bread with jam, two free range eggs, hash brown, grilled tomato, sausages, baked beans, brewed coffee, Tea or hot chocolate",
                                                new BigDecimal("45")),
                                new MenuItemDTO("bk10", "Arabic breakfast",
                                                "Freshly squeezed seasonal fruit juices, freshly sliced fruits, marinated black and green olives, cheese, labneh, foul mudammas cooked with garlic tomato & olive oil, shakshuka egg, brewed coffee, Tea or hot chocolate",
                                                new BigDecimal("40")))));

                // 8. Soups & Salads
                menuItems.put("soups & salads", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("sp1", "French onion",
                                                "Clear french onion soup served with bread rolls",
                                                new BigDecimal("65")),
                                new MenuItemDTO("sp2", "Lentil soup",
                                                "Traditional lentil soup served with crispy crouton and lemon wedges",
                                                new BigDecimal("45")),
                                new MenuItemDTO("sp3", "Tomato soup", "Old fashioned tomato soup with basil and cream",
                                                new BigDecimal("40")),
                                new MenuItemDTO("sp4", "Mushroom soup", "Creamy and rich mushroom soup",
                                                new BigDecimal("45")),
                                new MenuItemDTO("sp5", "Chicken clear soup",
                                                "So delicious chicken soup served with bread rolls, lemon wedges and butter",
                                                new BigDecimal("55")),
                                new MenuItemDTO("sp6", "Seafood soup", "Shrimp, squid, clam with creamy pink sauce",
                                                new BigDecimal("65")),
                                new MenuItemDTO("sl1", "Smoked salmon Nicoise salad",
                                                "Potato, green beans, anchovy, tomato, onion, black olive and accompanied with smoked salmon served with french dressing",
                                                new BigDecimal("45")),
                                new MenuItemDTO("sl2", "Caesar salad",
                                                "Crisp Romanian lettuce with zest Caesar dressing topped with shaved parmesan cheese, garlic croutons. Choice of grilled chicken, grilled shrimps or smoked salmon",
                                                new BigDecimal("35")),
                                new MenuItemDTO("sl3", "Greek salad",
                                                "Romaine, feta cheese, kalamata olive, cucumber, tomato, green pepper and bermuda onion accompanied with lemon dressing olive oil seasoning with sea salt oregano",
                                                new BigDecimal("35")),
                                new MenuItemDTO("sl4", "Rocca salad",
                                                "Fresh Rocca lettuce with onion, sumac, cherry tomato and lemon dressing",
                                                new BigDecimal("35")),
                                new MenuItemDTO("sl5", "Grilled pineapple avocado salad",
                                                "Grilled pineapple and avocado and watercress accompanied with lemon olive oil",
                                                new BigDecimal("45")))));

                // 9. Mains & Sandwiches
                menuItems.put("mains & sandwiches", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("mn1", "Beef steak",
                                                "300gm of beef your choice of tenderloin or Ribe eye served with steamed vegetables and french fries",
                                                new BigDecimal("70")),
                                new MenuItemDTO("mn2", "Arabic mix grilled",
                                                "Shish taouk, tika, lamb chops, served with french fries and arabic pickles",
                                                new BigDecimal("90")),
                                new MenuItemDTO("mn3", "Baby chicken",
                                                "Boneless baby chicken marinated in arabic spices served with french fries",
                                                new BigDecimal("55")),
                                new MenuItemDTO("mn4", "Grilled salmon",
                                                "Marinated grilled salmon served with grilled vegetables and french fries accompanied with lemon butter sauce",
                                                new BigDecimal("80")),
                                new MenuItemDTO("mn5", "Curry",
                                                "The famous indian spicy food with your choice of chicken or lamb served with white rice or paratha and green salad.",
                                                new BigDecimal("50")),
                                new MenuItemDTO("mn6", "Biryani",
                                                "Traditional indian food with your choice of chicken, lamb or vegetables with papadum and raita",
                                                new BigDecimal("45")),
                                new MenuItemDTO("sd1", "Club sandwich",
                                                "Double Decker sandwich with grilled chicken breast, fried egg, lettuce, tomato and mayonnaise served with french fries and coleslaw",
                                                new BigDecimal("45")),
                                new MenuItemDTO("sd2", "Chicken burger",
                                                "200gm ground chicken breast accompanied with fried egg, slice cheddar cheese, tomato, lettuce and mayonnaise",
                                                new BigDecimal("40")),
                                new MenuItemDTO("sd3", "Beef burger",
                                                "200gm ground beef accompanied with fried egg, slice cheddar cheese, tomato, lettuce and mayonnaise",
                                                new BigDecimal("50")),
                                new MenuItemDTO("sd4", "Steak sandwich",
                                                "Grilled strip steak on crisp french baguette accompanied with cheese, tomato, mayonnaise and crispy lettuce",
                                                new BigDecimal("55")),
                                new MenuItemDTO("sd5", "Chicken fajita",
                                                "Chicken is infused with a flavorful marinade, then cooked with bell peppers, onions and garlic Served with your favorite tortillas",
                                                new BigDecimal("35")))));

                // 10. Pasta & Pizza
                menuItems.put("pasta & pizza", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("p1", "Penne arrabbiata",
                                                "Penne pasta with tomato sauce and spicy red pepper served with parmesan cheese",
                                                new BigDecimal("55")),
                                new MenuItemDTO("p2", "Fusilli al pesto",
                                                "Fusilli pasta with italian pesto sauce, cream, olive oil and garlic served with parmesan cheese",
                                                new BigDecimal("50")),
                                new MenuItemDTO("p3", "Spaghetti bolgnese",
                                                "Spaghetti pasta served with minced beef in tomato sauce and parmesan cheese",
                                                new BigDecimal("50")),
                                new MenuItemDTO("p4", "Carbonara",
                                                "Choice of your pasta with mushroom, bacon, onion, garlic, egg yolk, cream and parmesan cheese",
                                                new BigDecimal("50")),
                                new MenuItemDTO("pz1", "Margherita", "Mozzarella cheese, Oregano, Basil, Tomato sauce",
                                                new BigDecimal("38")),
                                new MenuItemDTO("pz2", "Vegetable pizza",
                                                "Champignon, Capsicum, black olives, onion, Mozzarella cheese, Oregano, Tomato sauce",
                                                new BigDecimal("35")),
                                new MenuItemDTO("pz3", "Pepperoni",
                                                "Pepperoni, Mozzarella cheese, Oregano, Tomato sauce",
                                                new BigDecimal("48")),
                                new MenuItemDTO("pz4", "Frutti Di Mare",
                                                "Shrimps, calamari, Mozzarella cheese, Oregano, Tomato sauce",
                                                new BigDecimal("38")))));

                // 11. Dessert & Shisha
                menuItems.put("dessert & shisha", new CopyOnWriteArrayList<>(List.of(
                                new MenuItemDTO("d1", "Tiramisu", "", new BigDecimal("10")),
                                new MenuItemDTO("d2", "Carrot cake", "", new BigDecimal("12")),
                                new MenuItemDTO("d3", "Honey cake", "", new BigDecimal("30")),
                                new MenuItemDTO("d4", "Cheese cake", "", new BigDecimal("20")),
                                new MenuItemDTO("d5", "Devil cake", "", new BigDecimal("22")),
                                new MenuItemDTO("d6", "Um Ali", "", new BigDecimal("20")),
                                new MenuItemDTO("d7", "Ice cream", "", new BigDecimal("20")),
                                new MenuItemDTO("sh1", "Classic Shisha",
                                                "Roasted Cappuccino, Iced Raspberry Mint, Double apple, Watermelon, Blueberry, Orange, Lemon, Grape, Mint",
                                                new BigDecimal("80")),
                                new MenuItemDTO("sh2", "Double Flavors", "", new BigDecimal("80")),
                                new MenuItemDTO("sh3", "Triple Flavors", "", new BigDecimal("80")))));

                // Ensure natural insertion order by using LinkedHashMap for key iteration in
                // Response
                // Since ConcurrentHashMap doesn't guarantee order, we rely on the client to
                // sort keys or use an ordered structure wrapper if strict ordering is needed
                // for tabs.
                // For simplicity in this demo, ConcurrentHashMap suffices as frontend sorts or
                // just iterates.
        }
}
