package com.downtowncafe.controller;

import com.downtowncafe.dto.MenuItemDTO;
import com.downtowncafe.dto.MenuResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    @GetMapping("/menu")
    public MenuResponseDTO getMenu() {
        // Constructing the data to match the frontend expectations
        // This simulates a database fetch for now

        List<MenuItemDTO> breakfast = List.of(
                new MenuItemDTO("b1", "Burj Sunrise Shakshuka",
                        "Farm-fresh organic eggs gently poached in a slow-simmered tomato and roasted Bell pepper ragout, infused with saffron and served with artisan sourdough toast.",
                        new BigDecimal("65")),
                new MenuItemDTO("b2", "Truffle & Gold Avocado Toast",
                        "Toasted charcoal sourdough topped with smashed Hass avocado, Persian feta, shavings of black trifle, and a delicate dusting of 24k edible gold leaf.",
                        new BigDecimal("85")),
                new MenuItemDTO("b3", "Downtown Benedict",
                        "Smoked Wagyu beef brisket on a toasted brioche bun, crowned with velvety yuzu hollandaise and micro-greens.",
                        new BigDecimal("75")),
                new MenuItemDTO("b4", "Acai Jewel Bowl",
                        "Pure organic Acai blended with frozen berries, topped with house-made granola, dragon fruit, chia seeds, and a drizzle of wild sidr honey.",
                        new BigDecimal("55")),
                new MenuItemDTO("b5", "Parisian Croissant French Toast",
                        "Buttery croissant soaked in vanilla bean custard, pan-seared and served with caramelized figs, mascarpone cream, and roasted pistachios.",
                        new BigDecimal("60")));

        List<MenuItemDTO> lunch = List.of(
                new MenuItemDTO("l1", "Wagyu Truffle Burger",
                        "Premium Wagyu beef patty, melted gruyère, caramelized onions, and black truffle aioli on a house-baked brioche bun. Served with rosemary fries.",
                        new BigDecimal("95")),
                new MenuItemDTO("l2", "Saffron Risotto & Scallops",
                        "Creamy arborio rice infused with premium Iranian saffron, topped with pan-seared Hokkaido scallops and a parmesan crisp.",
                        new BigDecimal("110")),
                new MenuItemDTO("l3", "Downtown Lobster Roll",
                        "Chilled chunks of wild-caught lobster tossed in lemon-dill mayo, nestled in a warm, buttered split-top roll.",
                        new BigDecimal("125")),
                new MenuItemDTO("l4", "Quinoa & Pomegranate Salad",
                        "A refreshing mix of tri-color quinoa, roasted butternut squash, pomegranate jewels, baby kale, and toasted pine nuts with a lemon-tahini dressing.",
                        new BigDecimal("70")),
                new MenuItemDTO("l5", "Miso Glazed Black Cod",
                        "Sustainably sourced black cod marinated in sweet miso for 48 hours, baked to perfection and served with bok choy and jasmine rice.",
                        new BigDecimal("145")));

        List<MenuItemDTO> coffee = List.of(
                new MenuItemDTO("c1", "Gold Dust Latte",
                        "Our signature espresso blend with silky steamed milk, infused with Madagascar vanilla and dusted with 24k gold.",
                        new BigDecimal("45")),
                new MenuItemDTO("c2", "Saffron Cortado",
                        "A rich ristretto shot cut with warm milk and infused with premium saffron strands for an exotic, aromatic finish.",
                        new BigDecimal("35")),
                new MenuItemDTO("c3", "Cold Brew Tonic",
                        "Create a refreshing lift with our 18-hour slow-steeped cold brew, served over ice locally sourced premium tonic water and a slice of grapefruit.",
                        new BigDecimal("30")),
                new MenuItemDTO("c4", "Rose & Pistachio Mocha",
                        "Dark Belgian chocolate melted into espresso, topped with rose-infused milk and crushed pistachios.",
                        new BigDecimal("40")),
                new MenuItemDTO("c5", "Chemex Pour-Over (Single Origin)",
                        "Experience the pure notes of our seasonally rotating single-origin bean, hand-brewed for clarity and complexity.",
                        new BigDecimal("35")));

        Map<String, String> experience = Map.of(
                "signatureBlends",
                "Masterfully roasted beans sourced from the world's finest estates, brewed to perfection.",
                "chefsSpecials", "Culinary innovations that fuse local flavors with international techniques.",
                "artisanPastries",
                "Hand-crafted daily by our resident patissier, pairing perfectly with our specialty coffees.");

        return new MenuResponseDTO(
                Map.of("breakfast", breakfast, "lunch", lunch, "coffee", coffee),
                experience);
    }
}
