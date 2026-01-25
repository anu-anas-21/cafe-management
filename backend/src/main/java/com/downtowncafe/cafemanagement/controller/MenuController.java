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
        // Shisha & Special Flavors
        menuItems.put("Shisha", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("sh1", "Classic Shisha", "", new BigDecimal("50"), "Shisha"),
                new MenuItemDTO("sh2", "Double Flavors", "", new BigDecimal("55"), "Shisha"),
                new MenuItemDTO("sh3", "Triple Flavors", "", new BigDecimal("60"), "Shisha"),
                new MenuItemDTO("sh4", "Head Change", "", new BigDecimal("55"), "Shisha")
        )));
        // Coffee & Tea
        menuItems.put("Coffee & Tea", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("bc1", "Americano", "", new BigDecimal("22"), "Black Coffee"),
                new MenuItemDTO("bc2", "Turkish Coffee", "", new BigDecimal("20"), "Black Coffee"),
                new MenuItemDTO("hc1", "Single Espresso", "", new BigDecimal("18"), "Hot Coffee"),
                new MenuItemDTO("hc2", "Double Espresso", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc3", "Macchiato", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc4", "Flat White", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc5", "Piccolo", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc6", "Cappuccino", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc7", "Cafe Latte", "", new BigDecimal("22"), "Hot Coffee"),
                new MenuItemDTO("hc8", "Cafe Mocha", "", new BigDecimal("25"), "Hot Coffee"),
                new MenuItemDTO("hc9", "Spanish Latte", "", new BigDecimal("25"), "Hot Coffee"),
                new MenuItemDTO("hc10", "Caramel Macchiato", "", new BigDecimal("25"), "Hot Coffee"),
                new MenuItemDTO("hc11", "Hot Chocolate", "", new BigDecimal("25"), "Hot Coffee"),
                new MenuItemDTO("hc12", "Matcha Green Tea Latte", "", new BigDecimal("25"), "Hot Coffee"),
                new MenuItemDTO("ic1", "Iced Americano", "", new BigDecimal("22"), "Iced Coffee"),
                new MenuItemDTO("ic2", "Iced Brew", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("ic3", "Iced Cappuccino", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("ic4", "Iced Cafe Latte", "", new BigDecimal("22"), "Iced Coffee"),
                new MenuItemDTO("ic5", "Iced Cafe Mocha", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("ic6", "Iced Spanish Latte", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("ic7", "Iced Caramel Macchiato", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("ic8", "D Ice Shaken Espresso", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("ic9", "Affogato", "", new BigDecimal("25"), "Iced Coffee"),
                new MenuItemDTO("it1", "Peach", "", new BigDecimal("25"), "Iced Tea"),
                new MenuItemDTO("it2", "Lemon", "", new BigDecimal("25"), "Iced Tea"),
                new MenuItemDTO("ad1", "Vanilla, Caramel", "", new BigDecimal("5"), "Add On"),
                new MenuItemDTO("ad2", "Hazelnut, White Chocolate", "", new BigDecimal("5"), "Add On"),
                new MenuItemDTO("ht1", "English Breakfast", "", new BigDecimal("22"), "Hot Tea"),
                new MenuItemDTO("ht2", "Earl Grey", "", new BigDecimal("22"), "Hot Tea"),
                new MenuItemDTO("ht3", "Green Tea", "", new BigDecimal("20"), "Hot Tea"),
                new MenuItemDTO("ht4", "Chamomile", "", new BigDecimal("22"), "Hot Tea"),
                new MenuItemDTO("ht5", "Lipton Tea", "", new BigDecimal("20"), "Hot Tea"),
                new MenuItemDTO("ht6", "Jasmine Pearl", "", new BigDecimal("25"), "Hot Tea")
        )));
        // Cold Beverages
        menuItems.put("Cold Beverages", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("f1", "Classic", "", new BigDecimal("22"), "Frappuccino"),
                new MenuItemDTO("f2", "Caramel", "", new BigDecimal("25"), "Frappuccino"),
                new MenuItemDTO("f3", "Nutella", "", new BigDecimal("25"), "Frappuccino"),
                new MenuItemDTO("f4", "Coffee Frape", "", new BigDecimal("25"), "Frappuccino"),
                new MenuItemDTO("f5", "Double Shape Frape", "", new BigDecimal("25"), "Frappuccino"),
                new MenuItemDTO("f6", "Chocolate Chip Frappe", "", new BigDecimal("25"), "Frappuccino"),
                new MenuItemDTO("f7", "Matcha Green Tea", "", new BigDecimal("25"), "Frappuccino"),
                new MenuItemDTO("f8", "Vanilla, Caramel", "", new BigDecimal("5"), "Add on"),
                new MenuItemDTO("f9", "Hazelnut, White Chocolate", "", new BigDecimal("5"), "Add on"),
                new MenuItemDTO("iy1", "Strawberry", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("iy2", "Blueberry", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("iy3", "Mango", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("iy4", "Framboise", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("iy5", "Coconut", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("iy6", "Pineapple", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("iy7", "Crispy", "", new BigDecimal("25"), "Ice Yoghurt"),
                new MenuItemDTO("fj1", "Orange", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj2", "Pineapple", "", new BigDecimal("22"), "FreshJuices"),
                new MenuItemDTO("fj3", "Sweetmwlon", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj4", "Grapefruit", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj5", "Lemon/Mint", "", new BigDecimal("22"), "FreshJuices"),
                new MenuItemDTO("fj6", "Watermelon", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj7", "Kiwi", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj8", "Apple", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj9", "Mango", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj10", "Strawberry", "", new BigDecimal("22"), "FreshJuices"),
                new MenuItemDTO("fj11", "Carrot", "", new BigDecimal("20"), "FreshJuices"),
                new MenuItemDTO("fj12", "Cocktail", "", new BigDecimal("22"), "FreshJuices"),
                new MenuItemDTO("ms1", "Strawberry", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms2", "Vanilla", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms3", "Pistachio", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms4", "Chocolate", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms5", "Oreo", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms6", "Nutella", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms7", "Nutella Banana", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("ms8", "Avacado", "", new BigDecimal("25"), "Milkshakes"),
                new MenuItemDTO("sm1", "Strawberry", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm2", "Blueberry", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm3", "Mango", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm4", "Pina colada", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm5", "Chocolate chips", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm6", "Oreo", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm7", "Mixnuts", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("sm8", "Fruity", "", new BigDecimal("25"), "Smoothies"),
                new MenuItemDTO("fb1", "Healthy Mix", "Avocado, Basil, Ginger, Mint, Lemon Juice", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb2", "Detox Mix", "Basil, Cucumber, lemon Juice, Ginger", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb3", "Magic Mango", "Mango, Banana, Lemon, Coconut Milk, Mint", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb4", "Healthy Mix", "Avocado, Basil, Ginger, Mint, Lemon Juice", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb5", "Orange Kicker", "Orange Juice, Carrot, Fresh Ginger", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb6", "Mint Hint", "Mint, Green Apple, lemon Juice", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb7", "Red Notice", "Beetroot, Watermelon", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb8", "Watermelon Rose", "Watermelon, Rose Syrup", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("fb9", "Ultimate Green", "Mint, Lemon Juice, Sugar Syrup", new BigDecimal("25"), "Fresh Blends"),
                new MenuItemDTO("mok1", "Mojito", "Classic, Passion Fruit, Peach, Strawberry", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("mok2", "Sunrise", "Orange Juice, Grenadine Syrup, sprite", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("mok3", "Strawberry Fresco", "Strawberry Puree, Sprite", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("mok4", "Ibrik Mule", "Strawberry, Ginger Ale, Rosemary, Ginger Juice", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("mok5", "Blue Lagoon", "Lemon Juice, Sprite, Blue Coracco", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("mok6", "Rose", "Grape Fruit, Rose Syrup, Soda water", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("mok7", "Colada", "Pineapple, Coconut Syrup, Whipping Cream", new BigDecimal("25"), "Mocktails"),
                new MenuItemDTO("sd1", "Coca Cola", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd2", "Cola Diet", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd3", "Sprite", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd4", "Ginger Ale", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd5", "Vitamin C", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd6", "Mountain Dew", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd7", "Fanta", "", new BigDecimal("10"), "Soft Drinks"),
                new MenuItemDTO("sd8", "Red Bull", "", new BigDecimal("25"), "Soft Drinks"),
                new MenuItemDTO("w1", "Large Water", "", new BigDecimal("10"), "Water"),
                new MenuItemDTO("w2", "Small Water", "", new BigDecimal("7"), "Water"),
                new MenuItemDTO("w3", "Soda Water", "", new BigDecimal("12"), "Water"),
                new MenuItemDTO("w4", "Perrier Water(L)", "", new BigDecimal("20"), "Water"),
                new MenuItemDTO("w5", "Perrier Water(S)", "", new BigDecimal("15"), "Water")
        )));
        // Breakfast and Mornings
        menuItems.put("Breakfasts", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("bf1", "Three Eggs Selection", "Scrambled, fried, boiled, shakshuka, sunny side up, omelette or poached", new BigDecimal("35"), "Breakfast"),
                new MenuItemDTO("bf2", "Hummus with olive oil", "Served with arabic bread, cucumber, tomato and pickles", new BigDecimal("30"), "Breakfast"),
                new MenuItemDTO("bf3", "Labneh with olive oil", "Served with arabic bread, cucumber, tomato and pickles", new BigDecimal("30"), "Breakfast"),
                new MenuItemDTO("bf4", "Grilled halloumi cheese", "Served with arabic bread, cucumber, tomato and pickles", new BigDecimal("35"), "Breakfast"),
                new MenuItemDTO("bf5", "Pan cake or french toast", "Served with honey or syrup and whipped cream", new BigDecimal("35"), "Breakfast"),
                new MenuItemDTO("bf6", "Cereals", "Served with honey and milk", new BigDecimal("25"), "Breakfast"),
                new MenuItemDTO("bf7", "Pastry Basket", "Croissants, muffins and danish pastries with jam and butter", new BigDecimal("45"), "Breakfast"),
                new MenuItemDTO("bf8", "Continental breakfast", "Your choice of freshly squeezed seasonal, fruit juices, morning bakeries, freshly sliced fruits,fruit yogurt, toast bread with jam and butter, cereal brewed coffee, Tea or hot chocolate", new BigDecimal("60"), "Breakfast"),
                new MenuItemDTO("bf9", "English breakfast", "Your choice of freshly squeezed seasonal fruit juices, morning bakeries, freshly sliced fruits cereal, toast bread with jam and butter two free range eggs served with (hash brown potato, grilled tomato, sausages, baked beans) brewed coffee, Tea or hot chocolate", new BigDecimal("80"), "Breakfast"),
                new MenuItemDTO("bf10", "Arabic breakfast", "Your choice of freshly squeezed seasonal fruit juices, freshly sliced fruits marinated black and green olives, cheese, labneh foul mudammas cooked with garlic tomato & olive oil «shakshuka» egg with tomato, onion & parsley brewed coffee, Tea or hot chocolat", new BigDecimal("70"), "Breakfast")
        )));
        // Starters & Salads
        menuItems.put("Starters & Salads", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("sp1", "French Onion", "", new BigDecimal("25"), "Soups"),
                new MenuItemDTO("sp2", "Lentil Soup", "", new BigDecimal("25"), "Soups"),
                new MenuItemDTO("sp3", "Tomato Soup", "", new BigDecimal("25"), "Salads"),
                new MenuItemDTO("sp4", "Mushroom Soup", "", new BigDecimal("25"), "Salads"),
                new MenuItemDTO("sp5", "Chicken clear Soup", "", new BigDecimal("25"), "Salads"),
                new MenuItemDTO("sp6", "Vegetable Soup", "", new BigDecimal("20"), "Salads"),
                new MenuItemDTO("sp7", "Seafood Soup", "", new BigDecimal("30"), "Salads"),
                new MenuItemDTO("ap1", "Buffalo Wings", "", new BigDecimal("40"), "Appetizers"),
                new MenuItemDTO("ap2", "Vegetable spring rolls", "", new BigDecimal("40"), "Appetizers"),
                new MenuItemDTO("ap3", "Prawns tempura", "", new BigDecimal("65"), "Appetizers"),
                new MenuItemDTO("ap4", "Arabic hot mezzah", "", new BigDecimal("40"), "Appetizers"),
                new MenuItemDTO("ap5", "Crispy chicken bites", "", new BigDecimal("45"), "Appetizers"),
                new MenuItemDTO("sl1", "Smoked salmon Nicoise salad", "Potato, green beans, anchovy, tomato, onion, black olive and accompanied with smoked salmon served with frensh dressing", new BigDecimal("55"), "Salads"),
                new MenuItemDTO("sl2", "Caesar salad", "Crisp Romanian lettuce with zest Caesar dressing topped with shaved parmesan cheese, garlic croutons choise of grilled chicken, grilled shrimps or smoked salmon", new BigDecimal("45"), "Salads"),
                new MenuItemDTO("sl3", "Greek salad", "Romaine, feta cheese, kalamata olive, cucumber, tomato, green pepper and bermuda onion accompanied with lemon dressing olive oil seasoning with sea salt oregano", new BigDecimal("45"), "Salads"),
                new MenuItemDTO("sl4", "Green salad", "Assorted Green garden lettuice, cucumber, green pepper, asparagus, served with French dressing", new BigDecimal("35"), "Salads"),
                new MenuItemDTO("sl5", "Rocca salad", "Fresh Rocca lettuice with onion, sumac, cherry tomato and lemon dressing", new BigDecimal("35"), "Salads"),
                new MenuItemDTO("sl6", "Smoked salmon platter", "Thin slice of smoked salmon served with garden lettuice capers sliced onion brown toast bread and lemon wedges", new BigDecimal("65"), "Salads"),
                new MenuItemDTO("sl7", "Arabic cold mezzah", "Hummus, mutabbal, vine leaves, tabouleh and fattouch served with arabic bread", new BigDecimal("40"), "Salads"),
                new MenuItemDTO("sl8", "Grilled pineapple avocado salad", "Grilled pineapple and avocado and watercress accompanied with lemon olive oil", new BigDecimal("45"), "Salads")
        )));
        // Main Courses & Sandwiches
        menuItems.put("Main Courses & Sandwiches", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("mn1", "Beef steak", "300gm of beef your choice of tenderloin or Ribe eye served with steamed vegetables and french fries", new BigDecimal("90"), "Main Courses"),
                new MenuItemDTO("mn2", "Arabic mix grilled", "Shish taouk, tika, lamb chops, served with french fries and arabic pickles", new BigDecimal("70"), "Main Courses"),
                new MenuItemDTO("mn3", "Baby chicken", "Boneless baby chicken marinated in arabic spices served with french fries", new BigDecimal("55"), "Main Courses"),
                new MenuItemDTO("mn4", "Grilled salmon", "Marinated grilled salmon served with grilled vegetables and french fries accompanied with lemon butter sauce", new BigDecimal("80"), "Main Courses"),
                new MenuItemDTO("mn5", "Grilled Hammour", "Marinated grilled hammour served with grilled vegetables and french fries accompanied with lemon butter sauce", new BigDecimal("70"), "Main Courses"),
                new MenuItemDTO("mn6", "Curry", "The famous indian spicy food with your choice of chicken or lamb served with white rice or paratha and green salad", new BigDecimal("50"), "Main Courses"),
                new MenuItemDTO("mn7", "Biryani", "Traditional indian food with your choice of chicken, lamb or vegetables with papadum and raita", new BigDecimal("45"), "Main Courses"),
                new MenuItemDTO("mn8", "Nasi Goreng", "Traditional indonesian vegetables fried rice with your choice chicken or beef topped with fried egg, chicken sautee and peanut sauce", new BigDecimal("55"), "Main Courses"),
                new MenuItemDTO("sw1", "Club sandwich", "Double Decker sandwich with grilled chicken breast, fried egg, lettuce, tomato and mayonnaise served with french fries and coleslaw", new BigDecimal("45"), "Sandwiches"),
                new MenuItemDTO("sw2", "Chicken burger", "200gm ground chicken breast accompanied with fried egg, slice cheddar cheese, tomato, lettuce and mayonnaise", new BigDecimal("40"), "Sandwiches"),
                new MenuItemDTO("sw3", "Beef burger", "200gm ground beef accompanied with fried egg, slice cheddar cheese, tomato, lettuce and mayonnaise", new BigDecimal("50"), "Sandwiches"),
                new MenuItemDTO("sw4", "Downtown sandwich", "Slice of roast beef served in focaccia bread, Dijjon mustard, grilled onion, tomato and lettuce", new BigDecimal("45"), "Sandwiches"),
                new MenuItemDTO("sw5", "Steak sandwich", "Grilled strip steak on crisp french baguette accompanied with cheese, tomato, mayonnaise and crispy lettuce", new BigDecimal("55"), "Sandwiches"),
                new MenuItemDTO("sw6", "Warm Chicken sandwich", "Griiled chicken breast, tomato, lettuce, mayonnaise on french baguette", new BigDecimal("45"), "Sandwiches"),
                new MenuItemDTO("sw7", "Vegetable and cheese sandwich", "Marinated grilled vegetable in herbs served in baguette accompanied with grilled cheese, mayonnaise, lettuce and tomato", new BigDecimal("35"), "Sandwiches"),
                new MenuItemDTO("sw8", "Grilled halloumi sandwich", "Grilled halloumi sandwich served in arabic bread and oregano accompanied with tomato and cucumber", new BigDecimal("35"), "Sandwiches"),
                new MenuItemDTO("sw8", "Chicken fajita", "Chicken is infused with a flavorful marinade, then cooked with bell peppers, onions and garlic Served with your favorite tortillas", new BigDecimal("35"), "Sandwiches")
        )));
        // Pasta & Pizzas
        menuItems.put("Italian", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("pa1", "Penne arrabbiata", "Penne pasta with tomato sauce and spicy red pepper served with parmesan cheese", new BigDecimal("50"), "Pasta"),
                new MenuItemDTO("pa2", "Fusilli al pesto", "Fusilli pasta with italian pesto sauce, cream, olive oil and garlic served with parmesan cheese", new BigDecimal("55"), "Pasta"),
                new MenuItemDTO("pa3", "Spaghetti bolgnese", "Spaghetti pasta served with minced beef in tomato sauce and parmesan cheese", new BigDecimal("50"), "Pasta"),
                new MenuItemDTO("pa4", "Carbonara", "Choice of your pasta with mushroom, bacon, onion, garlic, egg yolk, cream and parmesan cheese", new BigDecimal("55"), "Pasta"),
                new MenuItemDTO("pz1", "Margherita", "Mozzarella cheese, Oregano, Basil, Tomato sauce", new BigDecimal("45"), "Pizza"),
                new MenuItemDTO("pz2", "Vegetable pizza", "Champignon, Capsicum (green, red and yellow), black olives, onion, Mozzarella cheese,Oregano, Tomato sauce", new BigDecimal("40"), "Pizza"),
                new MenuItemDTO("pz3", "pepperoni", "Pepperoni, Mozzarella cheese, Oregano, Tomato sauce", new BigDecimal("45"), "Pizza"),
                new MenuItemDTO("pz4", "Frutti Di Mare", "Shrimps, calamari, Mozzarella cheese,Oregano, Tomato sauce", new BigDecimal("45"), "Pizza"),
                new MenuItemDTO("pz5", "Hawaian", "Marinated Chicken, pineapple, Mozzarella cheese, Oregano, Tomato sauce", new BigDecimal("45"), "Pizza")
        )));
        // Desserts & Bakery
        menuItems.put("Desserts & Bakery", new CopyOnWriteArrayList<>(List.of(
                new MenuItemDTO("ds1", "Tiramisu", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds2", "Carrot cake", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds3", "Honey cake", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds4", "Cheese cake", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds5", "Devil cake", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds6", "Cake of the day", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds7", "Um Ali", "", new BigDecimal("22"), "Desserts"),
                new MenuItemDTO("ds8", "Fruits salad", "", new BigDecimal("22"), "Desserts"),
                new MenuItemDTO("ds9", "Ice cream", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds10", "Fresh Fruits Platter", "", new BigDecimal("30"), "Desserts"),
                new MenuItemDTO("ds11", "Muffins", "", new BigDecimal("12"), "Desserts"),
                new MenuItemDTO("ds12", "Croissants", "", new BigDecimal("10"), "Desserts"),
                new MenuItemDTO("ds13", "Cinnamon roll", "", new BigDecimal("20"), "Desserts"),
                new MenuItemDTO("ds14", "Donut", "", new BigDecimal("12"), "Desserts"),
                new MenuItemDTO("ck1", "1kg Regular Cake", "", new BigDecimal("120"), "Cakes"),
                new MenuItemDTO("ck2", "1kg Customized Cake", "", new BigDecimal("150"), "Cakes"),
                new MenuItemDTO("cks1", "1kg Chocolate", "", new BigDecimal("80"), "Cookies"),
                new MenuItemDTO("cks2", "1kg Chocolate chip", "", new BigDecimal("80"), "Cookies"),
                new MenuItemDTO("cks3", "1kg Chocolate with nuts", "", new BigDecimal("80"), "Cookies"),
                new MenuItemDTO("cks4", "1kg Cocounuts", "", new BigDecimal("80"), "Cookies"),
                new MenuItemDTO("cks5", "1kg Pistacio", "", new BigDecimal("80"), "Cookies")
        )));
    }
}
